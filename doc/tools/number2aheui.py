#!/usr/bin/env python
"""\
Number to Aheui code translator, revision 1
Copyright (c) 2005, Kang Seonghoon (Tokigun).

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
"""

import sys, re

class Generator:
    aheuimap = {
        0: u'\ubc14', 2: u'\ubc15\ubc18\ubc27', 3: u'\ubc1b\ubc2a\ubc2c',
        4: u'\ubc24\ubc25\ubc2b\ubc2d\ubc2e\ubc16\ubc17\ubc28',
        5: u'\ubc1c\ubc19\ubc1a', 6: u'\ubc26', 7: u'\ubc1d', 8: u'\ubc23',
        9: u'\ubc1e\ubc1f\ubc21\ubc22', '+': u'\ub2e4', '-': u'\ud0c0',
        '*': u'\ub530', '/': u'\ub098', '%': u'\ub77c', 'dup': u'\ube60',
    }

    def __init__(self, limit=65536):
        self.limit = limit
        self.table = {1: (2, 2, '/')}
        self.ntable = {1: 3}
        for i in [0,2,3,4,5,6,7,8,9]:
            self.table[i] = None
            self.ntable[i] = 1
        self.generation = 2

    def __getitem__(self, n):
        if self.table[n] is None: return (n,)
        return reduce(lambda x,y: x + (isinstance(y, str) and (y,) or self[y]), self.table[n], ())

    def generate(self):
        table, ntable, limit, generation = \
            self.table, self.ntable, self.limit, self.generation
        xtable = {}
        for k, v in ntable.iteritems():
            try: xtable[v].append(k)
            except: xtable[v] = [k]

        # type 1
        for x in xtable.get(generation - 2, []):
            if x * x <= limit and x * x not in table:
                table[x * x] = (x, 'dup', '*')
                ntable[x * x] = generation

        # type 2
        for i in xtable.iterkeys():
            for x in xtable.get(generation - i - 1, []):
                if x == 0: continue
                for y in xtable[i]:
                    if y == 0 or x <= y: continue
                    if x + y <= limit and x + y not in table:
                        table[x + y] = (x, y, '+')
                        ntable[x + y] = generation
                    if x - y <= limit and x - y not in table:
                        table[x - y] = (x, y, '-')
                        ntable[x - y] = generation
                    if x * y <= limit and x * y not in table:
                        table[x * y] = (x, y, '*')
                        ntable[x * y] = generation
                    if x / y <= limit and x / y not in table:
                        table[x / y] = (x, y, '/')
                        ntable[x / y] = generation

        self.generation += 1

    def expand(self, target):
        if target < 0:
            raise ValueError
        if self.limit < int(target * 1.1):
            self.limit = int(target * 1.1)
        while target not in self.table:
            self.generate()

    def get(self, n):
        if n not in self.table: self.expand(n)
        return self[n]

    def eval(self, expr, stack=None):
        if stack is None: stack = []
        for i in expr:
            if i in ('+','-','*','/','%'):
                b, a = stack.pop(), stack.pop()
                if i == '+': c = a + b
                elif i == '-': c = a - b
                elif i == '*': c = a * b
                elif i == '/': c = a / b
                elif i == '%': c = a % b
                stack.append(c)
            elif i == 'dup':
                stack.append(stack[-1])
            elif i == 'swap':
                stack[-1], stack[-2] = stack[-2], stack[-1]
            else:
                stack.append(int(i))
        return stack

    def offset(self, expr):
        offset = 0
        for i in expr:
            if i in ('+','-','*','/','%'): offset -= 1
            elif i == 'dup': offset += 1
            elif i == 'swap': offset += 0
            else: offset += 1
        return offset

    def toaheui(self, expr):
        return u''.join([self.aheuimap[i][0] for i in expr])

    eval = classmethod(eval)
    toaheui = classmethod(toaheui)

class Generator2(Generator):
    def __init__(self, base, limit=65536, gen=None):
        if limit < int(base * 1.1): limit = int(base * 1.1)
        if gen is None: gen = Generator(limit)
        self.base, self.limit, self.gen = base, limit, gen
        self.table = {base*base: ('dup', '*'), base: ()}
        self.ntable = {base*base: 2, base: 0}
        self.generation = 2

    def __getitem__(self, n):
        try:
            if self.ntable[n] < self.gen.ntable[n]:
                return reduce(lambda x,y: x + (isinstance(y, str) and (y,) or self.gen[y]), self.table[n], ())
        except: pass
        return self.gen[n]

    def generate(self):
        table, ntable, base, limit, generation = \
            self.table, self.ntable, self.base, self.limit, self.generation
        if generation >= self.gen.generation:
            self.gen.generate()

        xtable1, xtable2 = [], []
        for k, v in self.gen.ntable.iteritems():
            if generation == v + 1: xtable1.append(k)
            elif generation == v + 2: xtable2.append(k)

        for x in xtable2:
            if x <= base: continue
            if x - base <= limit and x - base not in table:
                table[x - base] = (x, 'swap', '-')
                ntable[x - base] = generation
            if x / base <= limit and x / base not in table:
                table[x / base] = (x, 'swap', '/')
                ntable[x / base] = generation

        for x in xtable1:
            if x == 0: continue
            if base + x <= limit and base + x not in table:
                table[base + x] = (x, '+')
                ntable[base + x] = generation
            if 0 < base - x <= limit and base - x not in table:
                table[base - x] = (x, '-')
                ntable[base - x] = generation
            if base * x <= limit and base * x not in table:
                table[base * x] = (x, '*')
                ntable[base * x] = generation
            if base / x <= limit and base / x not in table:
                table[base / x] = (x, '/')
                ntable[base / x] = generation

        self.generation += 1

    def expand(self, target):
        if target < 0:
            raise ValueError
        if self.limit < int(target * 1.1):
            self.limit = int(target * 1.1)
        while target not in self.table or target not in self.gen.table:
            self.generate()

try:
    import psyco
    psyco.bind(Generator.generate)
    psyco.bind(Generator2.generate)
except: pass

################################################################################

def version(apppath):
    print __doc__
    return 0

def help(apppath):
    print __doc__[:__doc__.find('\n\n')]
    print
    print "Usage: %s [options] <string>" % apppath
    print
    print "--help, -h"
    print "    prints help message."
    print "--version, -V"
    print "    prints version information."
    print "--encoding, -e"
    print "    set encoding of string."
    print "--number"
    print "    assume that <string> is number."
    print "--verbose, -v"
    print "    prints original strings with generated codes."
    print "--newline, -l"
    print "    appends trailing newline(\\n) to source string."
    return 0

def main(argv):
    import getopt, locale

    try:
        opts, args = getopt.getopt(argv[1:], "hVvnle:",
                ['help', 'version', 'verbose', 'number', 'newline', 'encoding='])
    except getopt.GetoptError:
        return help(argv[0])

    encoding = locale.getpreferredencoding()
    isnumber = verbose = newline = False
    for opt, arg in opts:
        if opt in ('-h', '--help'): return help(argv[0])
        if opt in ('-V', '--version'): return version(argv[0])
        if opt in ('-e', '--encoding'): encoding = arg
        if opt in ('-n', '--number'): isnumber = True
        if opt in ('-v', '--verbose'): verbose = True
        if opt in ('-l', '--newline'): newline = True
    if len(args) == 0:
        print __doc__[:__doc__.find('\n\n')]
        return 1

    try:
        ''.encode(encoding)
    except LookupError:
        print "There is no encoding named '%s'." % encoding
        return 1

    for arg in args:
        if verbose:
            print (isnumber and '%s:' or '"%s":') % arg,

        try:
            if isnumber:
                value = int(arg)
            else:
                value = arg.decode(encoding)
        except UnicodeDecodeError:
            print "Unicode decode error -- ignore."
            continue
        except ValueError:
            print "Invalid number -- ignore."
            continue

        if isinstance(value, int):
            sign = False
            if value < 0:
                value = -value
                sign = True
            gen = Generator()
            code = gen.toaheui(gen.get(value))
            if sign: code = u'\ubc14' + code + u'\ud0c0'
            code += u'\ub9dd'
            if newline: code += u'\ubc1c\ubc15\ub530\ub9e3'
            code += u'\ud76c'
        else:
            if newline: value += u'\n'
            xgen = gen = Generator()
            code = u''
            for char in re.findall('[1-9][0-9]{0,4}|.', value, re.S):
                mode = char.isdigit()
                if mode: i = int(char)
                else: i = ord(char)
                expr = xgen.get(i)
                if xgen.offset(expr) == 0:
                    code = code[:-1] + u'\ube60' + code[-1]
                code += xgen.toaheui(expr) + (mode and u'\ub9dd' or u'\ub9e3')
                xgen = Generator2(i, gen=gen)
            code += u'\ud76c'
        print code.encode(encoding)

    return 0

if __name__ == '__main__':
    sys.exit(main(sys.argv))

