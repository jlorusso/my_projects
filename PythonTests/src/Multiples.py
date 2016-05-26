'''
Created on Feb 21, 2015

@author: Jimmy
'''

multiples = 0
total = 0
for num in range(1000):
    if num % 5 == 0 or num % 3 == 0:
        multiples += 1
        total += num

print multiples
print total

if __name__ == '__main__':
    pass