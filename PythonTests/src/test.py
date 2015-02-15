import module1
from module1 import join_words

def printName(func):
    print "Hi John", func()

def getLast():
    return "Smith"

def printPersonDetails(name, age, sex="person", *varTup):
    print "{} is a {} year old {}".format(name, age, sex)
    
    if varTup:
        print "{}'s additional characteristics include:".format(name)
        for i in varTup:
            print " - {}".format(i)

def main():
    file_path = "C:\\Temp\\test.txt"
    with open(file_path, 'r') as fh:
        try:
            testVar = 100000
            for line in fh:
                print line.rstrip("\r\n")
            
            entire_file = fh.read();
            print entire_file
        except IOError:
            print "Error"
        
    printName(getLast)
    print
    print testVar
    
    printPersonDetails(sex="male", name="Joe", age="27")
    printPersonDetails(age="25", name="Sam")
    printPersonDetails("Paul", "46", "male", "Large", "Hairy")
    
    add = lambda arg1, arg2: arg1 + arg2
    
    print add(1, 3)
    print join_words(',', "apple", "orange", "banana")
    print join_words('&', "pear")
    print join_words('|', "grape", "melon")
    print "-".join(["1", "2", "3"])
    
    print "Globals: {}".format(globals())
    print "Locals: {}".format(locals())
    print "Local Keys: {}".format(locals().keys())
    

main()
print
print dir(module1)
print "File: '{}'\nName: '{}'\nDict: '{}'".format(module1.__file__, module1.__name__, module1.__dict__)

print "\n{}".format(__file__)
reload(module1)
print "\nDone"
