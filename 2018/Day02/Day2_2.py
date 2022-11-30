with open("2-2_input") as f:
    content = f.readlines()

history = []
for line in content:
    history.append(line)

    #print("Full history - ", history)
    #print("Line - ", line)
    for word in history:
        diffLetters = []
        #print("History - ", word)
        for i in range(len(word) - 1):
            if word[i] != line[i]:
                diffLetters.append(word[i])

        #print("Diff letters count - ", len(diffLetters))
        if len(diffLetters) == 1:
            print("Diff letters -", diffLetters)
            print("Diff line - ", line)
            print("Diff line from history - ", word)
            break

