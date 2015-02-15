import os
import sys

def change_dir():
    ret_val = False
    try:
        curr_dir = os.getcwd()
        print "Current directory is: '{}'.".format(curr_dir)
        new_dir = raw_input("Please enter a directory to change to: ")
        print "Changing to '{}'".format(new_dir)
        os.chdir(new_dir)
        print "Current directory is: '{}'.".format(os.getcwd())
        if os.getcwd() == new_dir:
            ret_val = True
    except OSError, arg:
        print "Error! Unable to change to new directory '%s'. Reason: %s" % (new_dir, arg)
        
    return ret_val

def writeFile(filename, content):
    with open(filename, "w") as fh:
        for line in content:
            fh.write(line+"\n")
            
def distance_from_zero(arg):
    type1 = type(arg)
    print type1
    if type1 == int or type1 == float:
        return abs(arg)
    else:
        return "Nope"

def main():
    ret_val = change_dir()
    if not ret_val:
        print "Warning: There was a problem changing directories."
        sys.exit(1)
    
    filename = "writeTest.txt"
    content = ("This is a line", "This is a second line", "", "Pineapple")
    print "Writing file to '%s\\%s'" % (os.getcwd(), filename)
    writeFile(filename, content)

num = 100000000000
if num == 10000000000 * 10:
    print True
else:
    print False
if num is 10000000000 * 10:
    print True
else:
    print False
#for i in range(250, 260): a = i; print "%i: %s" % (i, a is int(str(i)));
#main()