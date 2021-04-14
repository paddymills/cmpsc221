#!/bin/python3

import pyautogui
import os
import time

RES_X = 1920
RES_Y = 1080
WIDTH = 600 + 10
HEIGHT = 300 + 10

rleft = (RES_X / 2) - (WIDTH / 2)
rtop = (RES_Y / 2) - (HEIGHT / 2) + 12

def main():
    i = 1

    while True:
        filename = "screenshots/Run2_{}.png".format(i)
        if os.path.exists(filename):
            i += 1
            continue

        take(filename)
        break


def take(filename):

    pyautogui.screenshot(filename, region=(rleft, rtop, WIDTH, HEIGHT))
    print("Screenshot taken:", filename)

main()