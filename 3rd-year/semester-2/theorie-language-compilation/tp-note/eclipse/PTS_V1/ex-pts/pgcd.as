 	JMP 	L0:
L1: 	NOP 	
L4: 	NOP 	
 	LDL 	-4
 	LDL 	-3
 	SUB 	
 	JNZ 	L5:
 	JMP 	L3:
L5: 	NOP 	
 	LDL 	-3
 	LDL 	-4
 	SUB 	
 	JGZ 	L7:
 	JMP 	L6:
L6: 	NOP 	
 	LDL 	-4
 	LDL 	-3
 	SUB 	
 	LLA 	-4
 	STI 	
 	JMP 	L4:
L7: 	NOP 	
 	LDL 	-3
 	LDL 	-4
 	SUB 	
 	LLA 	-3
 	STI 	
 	JMP 	L4:
L3: 	NOP 	
 	LDL 	-4
 	STL 	-5
 	JMP 	L2:
L2: 	RET 	2
L0: 	NOP 	
 	INC 	2
 	RCV 	
 	LLA 	0
 	STI 	
L10: 	NOP 	
 	RCV 	
 	LLA 	1
 	STI 	
L11: 	NOP 	
 	INC 	1
 	LDL 	0
 	LDL 	1
 	JSR 	L1:
 	SND 	
L9: 	DEC 	2
L8: 	HLT 	

