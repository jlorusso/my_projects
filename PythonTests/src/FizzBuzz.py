
nums = range(1,101)

for num in nums:
    message = ""
    if (num%3)==0:
        message = "Fizz"
    if (num%5)==0:
        message = message + "Buzz"
    if(len(message) == 0):
        message = num
    
    print(message)