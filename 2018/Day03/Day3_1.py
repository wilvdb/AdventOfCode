class Claim(object):

    def __init__(self, claim_string):
        claim_strings = claim_string.split('@')
        coordinates = claim_strings[1].strip().split(':')
        self._left = int(coordinates[0].strip().split(',')[0])
        self._top = int(coordinates[0].strip().split(',')[1])
        self._width = int(coordinates[1].strip().split('x')[0])
        self._height = int(coordinates[1].strip().split('x')[1])
        #print(self._left, self._top, self._width, self._height)

    def width(self):
        return self._width

    def height(self):
        return self._height

    def left(self):
        return self._left

    def top(self):
        return self._top

def print_plan(plan):
    for line in plan:
        for cell in line:
            print(cell, end='')
        print('')

with open("3-1_input") as f:
    content = f.readlines()

plan = [[]]
for line in content:
    claim = Claim(line)
    total_width = claim.width() + claim.left()
    total_height = claim.height() + claim.top()
    for h in range(total_height):
        if len(plan) <= h:
            plan.append([])
        for w in range(total_width):
            if len(plan[h]) <= w:
                plan[h].append('.')
            if w >= claim.left() and h >= claim.top():
                if plan[h][w] == 'o':
                    plan[h][w] = 'x'
                elif plan[h][w] == '.':
                    plan[h][w] = 'o'

#print_plan(plan)

count = 0
for line in plan:
    for cell in line:
        if cell == 'x':
            count += 1

print("Count :", count)

