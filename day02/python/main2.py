#!/usr/bin/env python3

import os
import numpy as np

filename = '../sample_short.txt'
# filename = '../sample_long.txt'

with open(filename) as file:
    lines = []
    for line in file:
        lines.append([int(x) for x in line.split()])

out = 0
for line in lines:
    for ii in line:
        print([(l % ii) == 0 for l in line])

    # out += max(line) - min(line)

# print(out)
