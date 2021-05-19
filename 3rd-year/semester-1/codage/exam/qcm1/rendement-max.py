import math

som=0

p = int(input("Enter a value for p: "))

for i in range(5):
    som = som + math.comb(4*p,i)

print("p     = ",p)
print("somme = ",som)
print("puiss = ",2**(4*p-4))


'''
#foo=(1+5*p+(5*p*(5*p-1)/2)+(5*p*(5*p-1)*(5*p-2))/6)
bar=(1+p+(p*(p-1)/2)+(p*(p-1)*(p-2))/6)

print(2**(p-2))
'''

