 	JMP 	L0:
L0: 	NOP 	
 	INC 	2
 	LDC 	0
 	STL 	0
 	LDC 	9
 	STL 	0
L3: 	LDL 	0
 	LDL 	0
 	SUB 	
 	JGZ 	L4:
 	JMP 	L2:
L4: 	NOP 	
 	LDL 	0
 	SND 	
L5: 	LDC 	1
 	LDL 	0
 	ADD 	
 	STL 	0
L2: 	JMP 	L3:
L2: 	DEC 	2
L1: 	HLT 	

