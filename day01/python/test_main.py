#!/usr/bin/env python3

import pytest

from main import solve_captcha

@pytest.mark.parametrize(
    'num,out', [
        ('1122', 3),
        ('1111', 4),
        ('1234', 0),
        ('91212129', 9),
    ])
def test_solve_captcha(num, out):
    assert solve_captcha(num) == out
