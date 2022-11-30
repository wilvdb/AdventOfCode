import string

with open("2-1_input") as f:
    content = f.readlines()

alphabet = string.ascii_lowercase[:26]
two = 0
three = 0
for line in content:
    exactlyTwo = False
    exactlyThree = False
    print(line)
    for letter in alphabet:
        if line.count(letter) == 3:
            print("--3--", letter)
            exactlyThree = True
        if not line.count(letter) == 3 and line.count(letter) == 2:
            print("--2--", letter)
            exactlyTwo = True

    if exactlyTwo:
        two += 1
        print("Count two ", two)
    if exactlyThree:
        three += 1
        print("Count three ", three)

print(two)
print(three)
print(two * three)