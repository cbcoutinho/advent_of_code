#!/usr/bin/env python3

import sys

def solve_captcha(num):
    out = 0
    for ii in range(len(num)):
        if (ii == len(num)-1 and num[ii] == num[0]) or (ii != len(num)-1 and num[ii] == num[ii+1]):
            out += int(num[ii])

    return out

def solve_captcha2(num):
    out = 0

    # NOTE: This assumes `len(num)` is even by using
    # truncating/integer division
    halfway = len(num) // 2

    # Get around list wrap around/overflow issue
    _num = num+num
    for ii in range(len(num)):
        if _num[ii] == _num[ii+halfway]:
            out += int(num[ii])

    return out

if __name__ == '__main__':

    # Parse input number into string
    if len(sys.argv) > 1:
        num = sys.argv[1]
    else:
        raise ValueError("Must submit at least one input")

    # out = solve_captcha(num)
    out = solve_captcha2(num)
    print(out)

