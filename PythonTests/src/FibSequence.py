'''
Created on Feb 21, 2015

@author: Jimmy
'''

import sys
def getNextNum(num1, num2):
    for num in range(100):
        print "%s, " % num
 
getNextNum(0, 0)
sys.exit(0)

total = 0
prev = 0
curr = 1
next_num = 1
while next_num < 4000000:
    if next_num % 2 == 0:
        total += next_num
    next_num += prev

prev = 0
for num in range(1, 4000000, num+prev):
    if num % 2 == 0:
        total += num
    prev = num

print total

if __name__ == '__main__':
    pass