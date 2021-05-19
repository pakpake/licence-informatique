 	GBL 	2
 	JMP 	L0:
L1: 	NOP 	
 	INC 	1
 	LDL 	-4
 	LLA 	0
 	STI 	
L4: 	NOP 	
 	LDL 	-3
 	LLA 	-4
 	STI 	
L5: 	NOP 	
 	LDL 	0
 	LLA 	-3
 	STI 	
L3: 	DEC 	1
L2: 	RET 	2
L0: 	NOP 	
 	RCV 	
 	LGA 	0
 	STI 	
L7: 	NOP 	
 	RCV 	
 	LGA 	1
 	STI 	
L8: 	NOP 	
 	INC 	1
 	LDG 	0
 	LDG 	1
 	JSR 	L1:
L9: 	NOP 	
 	LDG 	0
 	SND 	
L10: 	NOP 	
 	LDG 	1
 	SND 	
L6: 	HLT 	

