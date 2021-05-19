 	GBL 	1
 	JMP 	L0:
L0: 	NOP 	
 	LDC 	0
 	LGA 	0
 	STI 	
L2: 	NOP 	
L3: 	NOP 	
 	JMP 	L4:
L4: 	NOP 	
 	LDG 	0
 	SND 	
L5: 	NOP 	
 	LDG 	0
 	LDC 	10
 	SUB 	
 	JNZ 	L6:
 	JMP 	L1:
L6: 	NOP 	
 	LDG 	0
 	LDC 	1
 	ADD 	
 	LGA 	0
 	STI 	
 	JMP 	L3:
L1: 	HLT 	

