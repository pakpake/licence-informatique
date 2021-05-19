 	GBL 	1
 	JMP 	L0:
L1: 	NOP 	
 	INC 	1
 	LDL 	-3
 	LDC 	0
 	SUB 	
 	JNZ 	L7:
 	JMP 	L5:
L5: 	NOP 	
 	LDC 	0
 	LLA 	0
 	STI 	
 	LDL 	-3
 	LDC 	1
 	SUB 	
 	JNZ 	L7:
 	JMP 	L6:
L6: 	NOP 	
 	LDC 	1
 	LLA 	0
 	STI 	
 	JMP 	L4:
L7: 	NOP 	
 	INC 	1
 	LDL 	-3
 	LDC 	1
 	SUB 	
 	JSR 	L1:
 	INC 	1
 	LDL 	-3
 	LDC 	2
 	SUB 	
 	JSR 	L1:
 	ADD 	
 	LLA 	0
 	STI 	
L4: 	NOP 	
 	LDL 	0
 	STL 	-4
 	JMP 	L2:
L3: 	DEC 	1
L2: 	RET 	1
L0: 	NOP 	
 	RCV 	
 	LGA 	0
 	STI 	
L9: 	NOP 	
 	INC 	1
 	LDG 	0
 	JSR 	L1:
 	SND 	
L8: 	HLT 	

