 	JMP 	L0:
L1: 	NOP 	
 	LDL 	-4
 	LDL 	-3
 	ADD 	
 	STL 	-5
 	JMP 	L2:
L2: 	RET 	2
L0: 	NOP 	
 	INC 	2
 	RCV 	
 	LLA 	0
 	STI 	
L5: 	NOP 	
 	RCV 	
 	LLA 	1
 	STI 	
L6: 	NOP 	
 	INC 	1
 	LDL 	0
 	LDL 	1
 	JSR 	L1:
 	SND 	
L4: 	DEC 	2
L3: 	HLT 	

