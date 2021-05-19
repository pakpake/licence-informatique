 	GBL 	2
 	JMP 	L0:
L0: 	NOP 	
 	LDC 	0
 	LGA 	0
 	STI 	
L2: 	NOP 	
L3: 	NOP 	
 	JMP 	L4:
L4: 	NOP 	
 	LDC 	0
 	LGA 	1
 	STI 	
L5: 	NOP 	
L7: 	NOP 	
 	JMP 	L8:
L8: 	NOP 	
 	LDG 	1
 	SND 	
L9: 	NOP 	
 	LDG 	1
 	LDG 	0
 	SUB 	
 	JNZ 	L10:
 	JMP 	L6:
L10: 	NOP 	
 	LDG 	1
 	LDC 	1
 	ADD 	
 	LGA 	1
 	STI 	
 	JMP 	L7:
L6: 	NOP 	
 	LDG 	0
 	LDC 	9
 	SUB 	
 	JNZ 	L11:
 	JMP 	L1:
L11: 	NOP 	
 	LDG 	0
 	LDC 	1
 	ADD 	
 	LGA 	0
 	STI 	
 	JMP 	L3:
L1: 	HLT 	

