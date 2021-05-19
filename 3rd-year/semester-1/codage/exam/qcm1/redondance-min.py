import math

som=0

p = int(input("Enter a value for p: "))

for i in range(4):
    som = som + math.comb(5*p,i)

print("p     = ",p)
print("somme = ",som)
print("puiss = ",2**(5*p-5))


'''
#foo=(1+5*p+(5*p*(5*p-1)/2)+(5*p*(5*p-1)*(5*p-2))/6)
bar=(1+p+(p*(p-1)/2)+(p*(p-1)*(p-2))/6)

print(2**(p-2))
'''

