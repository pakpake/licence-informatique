 	JMP 	L0:
L0: 	NOP 	
 	INC 	2
 	LDC 	9
 	LLA 	1
 	STI 	
L3: 	NOP 	
 	INC 	2
 	LDC 	0
 	LDL 	1
 	MUL 	
 	STL 	2
 	LDL 	1
 	LDC 	1
 	MUL 	
 	STL 	2
L5: 	LDL 	2
 	LDL 	2
 	SUB 	
 	JGZ 	L6:
 	JMP 	L4:
L6: 	NOP 	
 	LDL 	2
 	SND 	
L8: 	NOP 	
 	LDL 	1
 	LDC 	1
 	ADD 	
 	LLA 	1
 	STI 	
L7: 	LDC 	1
 	LDL 	2
 	ADD 	
 	STL 	2
L4: 	JMP 	L5:
L4: 	DEC 	2
L2: 	DEC 	2
L1: 	HLT 	

