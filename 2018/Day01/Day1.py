initValue = 0

with open("1_input") as f:
    content = f.readlines()

# part 1
for line in content:
    initValue += int(line)

print(initValue)

# part 2
values = [0]
twice = -1
i = 1
while twice == -1:
    print("Read ", i)
    i += 1
    for line in content:
        current = values[-1] + int(line)
        if current in values:
            print(current)
            twice = current
            break
        else:
            values.append(current)

print(twice)