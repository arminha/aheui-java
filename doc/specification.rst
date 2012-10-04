Aheui
=====

Aheui(아희) is an esoteric programming language, first ever to be designed for
the `Hangul <http://en.wikipedia.org/wiki/Hangul>`__/Hangeul.
The aim of the language is to reflect the graphical design of Hangul.

"Aheui"
-------

"아희"("aheui") is an archaic term for "child" or "youngster", mainly found as an
idiom "아희야"("aheui-ya", coincidently mathches with
"`Oh,_boy! <http://en.wikipedia.org/wiki/Oh_Boy>`__") in 15th-to-18th-century
Korean poetry called `Sijo <http://en.wikipedia.org/wiki/Sijo>`__.
IPA notation for pronunciation of "aheui" is
/ɑ.hɨj/(X-SAMPA \`/A.h1j/\`), or /ɑ.hɰi/(\`/A.hM\\i/\`). However, since the English
language has no exact equivalent sounds of /ɨ/ and /ɰ/, it is acceptable to
call it "AH-hi"/ɑ.hi/.

Structure of Hangul
-------------------

Also known as "Korean alphabet", Hangul is truly an alphabetic system, in which
each symbol represents separate phoneme, or sound.

::

     아희

Every Hangul letter has a structure, that is, a constant is juxtaposed or
surrounded by a vowel. You can recognize vowel symbols easily, as they are bar-
shaped, often with an attached little rod or two. All of Hangul vowel symbols
are following:

::

     ㅏㅓㅗㅜㅡㅣ
     ㅑㅕㅛㅠㅢ

     ㅘㅚ　ㅐㅙ
     ㅝㅟ　ㅔㅞ

Last eight complex vowels are not recognized as a feature of Aheui but as
ornamental symbols; so you can ignore them.

Lecturing on which-symbol-stands-for-which-sound is too far from the purpose of
this document. However, it is notable that similar-looking symbols represent
similar sound, as all consonants can be classified into five groups:

::

     ㄱㅋ
     ㄴㄷㅌㄹ
     ㅁㅂㅍ
     ㅅㅈㅊ
     ㅇㅎ

These consonants can also be placed under each letter, as well as on the top of
the character surrounded by a vowel. The consonant on the top is called the
"initial" consonant while on the bottom is the "final" consonant.

::
 
     안흼
 
In Aheui, functions of consonants and vowels are different each other.

Aheui specification
-------------------

.. WARNING:: 
   This spec is incomplete and subject to change.
 
Typical code of Aheui is a two-dimensional space of Hangul characters, where
each character is a command. In there cursor is moving around to execute the
character which is under the cursor, similar to the instruction pointer of
Funge-98.
 
As mentioned above, consonants and vowels have different functions. While the
consonant is the actual instruction, the vowel is to determine the cursor's
next position. Precisely, when the cursor meets a character, it is engaged to
the following process:

* Cursor executes the command, according to the initial consonant of the
  character. Final consonant might be used as an argument, if needed by the
  initial.
* Cursor moves, according to the vowel's direction.

There is more than one storage in Aheui. Most of them stacks, and one queue.
Also the extension feature is treated as a quasi-stack where you "push" the
input packets and "pop" the output packets. In any of the instructions
involving a pop or two, including duplication, if a storage has less element
than the instruction requires, the cursor doesn't execute the instruction but
moves according to the vowel only.

Vowels: the Directions
######################

Little rod attached to the bar indicates the direction of the cursor.

* ㅏ, ㅓ, ㅗ, and ㅜ moves cursor by one character right, left, top, bottom
  respectively.
* ㅑ, ㅕ, ㅛ, and ㅠ moves cursor by two characters right, left, top, bottom
  respectively.
* ㅡ, ㅣ, and  ㅢ are the "reflectors". The cursor moves back to where it had
  come from. While ㅢ reflects all movement of the cursor, ㅡ reflects only
  vertical movement of the cursor, as ㅣ does horizontal movement only. The
  movement which is not reflected moves in the direction it went, by the
  law of inertia.

Consonants: the Commands
########################

The ㅇ group: null

* ㅇ, as it is shaped, is the **null** command, which does nothing. The cursor
  will immediately move as the vowel indicates.
* ㅎ is the **terminate** command. The cursor stops at the character and
  terminates the execution.

Hence "아희" itself is the code, which would be a null program.

|

The ㄴ group: arithmatic

* ㄷ is the **add** command. The cursor pops two element from the current
  storage, and pushes the sum of two values.
* ㄸ is shaped double-ㄷ, hence the **multiply** command. The cursor pops two
  element from the current storage, and pushes the product of two values.
* ㄴ is the divide command. The cursor pops two element from the current
  storage, divide latter value by former value(latter/former), and pushes
  the result.
* ㅌ is the **subtract** command. The cursor pops two element from the current
  storage, subtract former value from latter value(latter-former), and
  pushes the result.
* ㄹ is the **modulo** command. The cursor pops two element from the current
  storage, and pushes (latter value) modulo (former value)

|

The ㅁ group: storage

* ㅁ is the **pop** command. The cursor pops an element from the current
  storage. Final consonant can be applied by case. (See below_.)
* ㅂ is the **push** command. The cursor pushes an element to the current
  storage. The value to push is specified by the final consonant. (See
  below_.)
* ㅃ, double-ㅂ, is the **duplicate** command. If the current storage is a stack,
  the cursor copies the top element. If the current storage is a queue, the
  cursor copies the front element.
* ㅍ is the **swap** command. The cursor swaps two elements .

.. _below:

In ㅁ command, only ㅇ and ㅎ are recognized as a final consonant. With ㅇ, the
cursor pops an element from the storage and prints it as an integer. ㅎ
specifies the cursor prints the character corresponding the value as the
Unicode codepoint.
That is same as in ㅂ command, where ㅇ specifies the cursor to receives an
integer from user input and pushes it into the storage, and ㅎ receives a
character and pushes the Unicode code point. And other final consonant of the ㅂ
command stands for the value to be pushed into the storage, which is specified
by the line segments that the consonant is consist of:

=== === === === === === === === === === === ===
 ㄱ  ㄴ  ㄷ  ㄹ  ㅁ  ㅂ  ㅅ  ㅈ  ㅊ  ㅋ  ㅌ  ㅍ
 2   2   3   5   4   4   2   3   4   3   4   4
=== === === === === === === === === === === ===

In Korean language, these "consonant clusters" are also valid as placed in the
final. They are also used in Aheui  usefully for higher number, if you can
remember which one is valid:

=== === === === === === === === === === === === ===
 ㄲ  ㄳ  ㄵ  ㄶ  ㄺ  ㄻ  ㄼ  ㄽ  ㄾ  ㄿ  ㅀ  ㅄ  ㅆ
 4   4   5   5   7   9   9   7   9   9   8   6   4
=== === === === === === === === === === === === ===

ㅂ command with no final consonant pushes zero. There is no way to push 1
explicitly, but it is possible by simple arithmetic, such as `반반나`(2/2), `밤받타`
(4-3).

|

The ㅅ group: miscellaneous

* ㅅ is **select** command, where the cursor selects the storage. The final
  consonant specifies which storage is to be selected. (See below.)
* ㅆ is **transfer** command. The cursor pops an element from the current
  storage, and pushes it into the storage that the final consonant
  indicates.
* ㅈ is shaped like balance scales, and this is the **compare** command. The
  cursor pops two elements from the current storage. If the latter value is
  grater than or equal to the former value, the cursor pushes 1, otherwise 0.
* ㅊ has many forks, so it is the **decide** command. The cursor pops an element
  from the current storage. If the value is non-zero, the cursor moves as
  is specified by the vowel. If the value is zero, the cursor moves
  *opposite* from the vowel's direction.

Storages you can specify with ㅅ and ㅆ commands are (none), ㄱ, ㄴ, ㄷ, ㄹ, ㅁ, ㅂ,
ㅇ, ㅅ, ㅈ, ㅊ, ㅋ, ㅌ, ㅍ, ㅎ, ㄲ, ㄳ, ㄵ, ㄶ, ㄺ, ㄻ, ㄼ, ㄽ, ㄾ, ㄿ, ㅀ, ㅄ, and ㅆ. All are
stacks except ㅇ, which is the queue, and ㅎ, which is the protocol the cursor
communicate with an extension.
The default storage which is selected at the very beginning of the execution is
the (none) stack.

Implementation
##############

The code of Aheui is written in UTF-8 encoding. Only Hangul syllables (from
U+!AC00 to U+!D7A3) are recognized as a command; others are ignored.

Example
#######

This code prints "Hello, world!"

::

   1. 밤밣따빠밣밟따뿌
   2. 빠맣파빨받밤뚜뭏
   3. 돋밬탕빠맣붏두붇
   4. 볻뫃박발뚷투뭏붖
   5. 뫃도뫃희멓뭏뭏붘
   6. 뫃봌토범더벌뿌뚜
   7. 뽑뽀멓멓더벓뻐뚠
   8. 뽀덩벐멓뻐덕더벅

Acknowledgement
###############

This document is based on the
`original Specification <http://puzzlet.springnote.com/pages/219154.xhtml>`__
written by Puzzlet Chung.
