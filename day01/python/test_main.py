#!/usr/bin/env python3

import pytest

from main import solve_captcha, solve_captcha2

@pytest.mark.parametrize(
    'num,out', [
        ('1122', 3),
        ('1111', 4),
        ('1234', 0),
        ('91212129', 9),
    ])
def test_solve_captcha(num, out):
    assert solve_captcha(num) == out

@pytest.mark.parametrize(
    'num, out', [
        ('1212', 6),
        ('1221', 0),
        ('123425', 4),
        ('123123', 12),
        ('12131415', 4),
    ])
def test_solve_captcha2(num, out):
    assert solve_captcha2(num) == out
