a = intSet() 
a.insert(1)
a.insert(2)
a.insert(3)
a.insert(4)

print(a.vals)

self.vals.sort()
        return '{' + ','.join([str(e) for e in self.vals]) + '}'
    
        def intersect(self, other):
        """Assumes other is an intSet
           Returns a new intSet containing elements that appear in both sets."""
        # Initialize a new intSet    
        commonValueSet = intSet()
        # Go through the values in this set
        for val in self.vals:
            # Check if each value is a member of the other set    
            if other.member(val):
                commonValueSet.insert(val)
        return commonValueSet
    
    
    OU 
    
        def intersect(self, other):
        self.result = []
        for i in self.vals:
            if i in other.vals:
                self.result.append(i)
        return '{' + ','.join([str(f) for f in self.result]) + '}' 
            