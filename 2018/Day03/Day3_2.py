class Claim(object):

    def __init__(self, claim_string):
        claim_strings = claim_string.split('@')
        coordinates = claim_strings[1].strip().split(':')
        self._id = int(claim_strings[0].strip()[1:])
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

    def id(self):
        return self._id

def print_plan(plan):
    for line in plan:
        for cell in line:
            print(cell, end='')
        print('')

with open("3-2_input") as f:
    content = f.readlines()

not_recovered = {}
plan = [[]]
for line in content:
    claim = Claim(line)
    total_width = claim.width() + claim.left()
    total_height = claim.height() + claim.top()
    not_recovered[claim.id()] = True
    for h in range(total_height):
        if len(plan) <= h:
            plan.append([])
        for w in range(total_width):
            if len(plan[h]) <= w:
                plan[h].append('.')
            if w >= claim.left() and h >= claim.top():
                if plan[h][w] != '.':
                    not_recovered[claim.id()] = False
                    not_recovered[plan[h][w]] = False
                elif plan[h][w] == '.':
                    plan[h][w] = claim.id()

for el in not_recovered:
    if not_recovered[el]:
        print(el)