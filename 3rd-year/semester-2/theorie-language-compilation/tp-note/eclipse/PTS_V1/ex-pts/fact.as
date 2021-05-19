 	GBL 	1
 	JMP 	L0:
L1: 	NOP 	
 	LDL 	-3
 	LDC 	0
 	SUB 	
 	JNZ 	L4:
 	JMP 	L3:
L3: 	NOP 	
 	LDC 	1
 	STL 	-4
 	JMP 	L2:
 	JMP 	L2:
L4: 	NOP 	
 	LDL 	-3
 	INC 	1
 	LDL 	-3
 	LDC 	1
 	SUB 	
 	JSR 	L1:
 	MUL 	
 	STL 	-4
 	JMP 	L2:
L2: 	RET 	1
L0: 	NOP 	
 	RCV 	
 	LGA 	0
 	STI 	
L6: 	NOP 	
 	INC 	1
 	LDG 	0
 	JSR 	L1:
 	SND 	
L5: 	HLT 	

