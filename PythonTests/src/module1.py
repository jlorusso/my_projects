class Parent(object):
    x = 1
    
class Child1(Parent):
    pass

class Child2(Parent):
    pass

class Animal(object):
    
    __species = ""
    
    def __init__(self, species):
        self.__species = species

    def move(self):
        print "The animal moved."

    def printAnimal(self):
        print "I am a {}".format(self.__species)
        
    def getSpecies(self):
        return self.__species

class Dog(Animal):
    
    def __init__(self):
        super(Dog, self).__init__("Dog")
        
    def move(self):
        print "The {} ran.".format(self.getSpecies())
        print "The {} ran fast.".format(getattr(self, self.__species))

def join_words(delimeter, word1, *words):
    joined = word1
    for word in words:
        joined += delimeter + word
    
    return joined

def main():
    parent = Child1.x
    print parent
    
    panda = Animal("Panda")
    dog = Dog()
    panda.printAnimal()
    dog.move()
    panda.move()
    
    for i in range(1, 11, 2):
        print i
   
    
main()