 	GBL 	100
 	JMP 	L0:
L1: 	NOP 	
 	INC 	2
 	RCV 	
 	LLA 	1
 	STI 	
L4: 	NOP 	
 	LDC 	0
 	LLA 	0
 	STI 	
L5: 	NOP 	
L7: 	NOP 	
 	LDL 	0
 	LDL 	1
 	SUB 	
 	JGZ 	L6:
 	JMP 	L8:
L8: 	NOP 	
 	RCV 	
 	LDL 	0
 	LDC 	1
 	MUL 	
 	LGA 	0
 	ADD 	
 	STI 	
L9: 	NOP 	
 	LDL 	0
 	LDC 	1
 	ADD 	
 	LLA 	0
 	STI 	
 	JMP 	L7:
L6: 	NOP 	
 	LDL 	1
 	STL 	-3
 	JMP 	L2:
L3: 	DEC 	2
L2: 	RET 	0
L10: 	NOP 	
 	INC 	1
 	LDL 	-4
 	LDC 	1
 	MUL 	
 	LGA 	0
 	ADD 	
 	LDI 	
 	LLA 	0
 	STI 	
L13: 	NOP 	
 	LDL 	-3
 	LDC 	1
 	MUL 	
 	LGA 	0
 	ADD 	
 	LDI 	
 	LDL 	-4
 	LDC 	1
 	MUL 	
 	LGA 	0
 	ADD 	
 	STI 	
L14: 	NOP 	
 	LDL 	0
 	LDL 	-3
 	LDC 	1
 	MUL 	
 	LGA 	0
 	ADD 	
 	STI 	
L12: 	DEC 	1
L11: 	RET 	2
L15: 	NOP 	
 	INC 	1
 	LDC 	0
 	LLA 	0
 	STI 	
L18: 	NOP 	
L19: 	NOP 	
 	LDL 	0
 	LDL 	-3
 	LDC 	1
 	SUB 	
 	SUB 	
 	JGZ 	L16:
 	JMP 	L20:
L20: 	NOP 	
 	INC 	1
 	LDL 	0
 	LLA 	1
 	STI 	
L22: 	NOP 	
L24: 	NOP 	
 	LDL 	1
 	LDL 	-3
 	SUB 	
 	JGZ 	L23:
 	JMP 	L25:
L25: 	NOP 	
 	LDL 	1
 	LDC 	1
 	MUL 	
 	LGA 	0
 	ADD 	
 	LDI 	
 	LDL 	0
 	LDC 	1
 	MUL 	
 	LGA 	0
 	ADD 	
 	LDI 	
 	SUB 	
 	JGZ 	L26:
 	JMP 	L27:
L27: 	NOP 	
 	INC 	1
 	LDL 	0
 	LDL 	1
 	JSR 	L10:
L26: 	NOP 	
 	LDL 	1
 	LDC 	1
 	ADD 	
 	LLA 	1
 	STI 	
 	JMP 	L24:
L23: 	NOP 	
 	LDL 	0
 	LDC 	1
 	ADD 	
 	LLA 	0
 	STI 	
L21: 	DEC 	1
 	JMP 	L19:
L17: 	DEC 	1
L16: 	RET 	1
L28: 	NOP 	
 	INC 	1
 	LDC 	0
 	LLA 	0
 	STI 	
L31: 	NOP 	
L32: 	NOP 	
 	LDL 	0
 	LDL 	-3
 	SUB 	
 	JGZ 	L29:
 	JMP 	L33:
L33: 	NOP 	
 	LDL 	0
 	LDC 	1
 	MUL 	
 	LGA 	0
 	ADD 	
 	LDI 	
 	SND 	
L34: 	NOP 	
 	LDL 	0
 	LDC 	1
 	ADD 	
 	LLA 	0
 	STI 	
 	JMP 	L32:
L30: 	DEC 	1
L29: 	RET 	1
L0: 	NOP 	
 	INC 	1
 	INC 	1
 	JSR 	L1:
 	LLA 	0
 	STI 	
L37: 	NOP 	
 	INC 	1
 	LDL 	0
 	JSR 	L15:
L38: 	NOP 	
 	INC 	1
 	LDL 	0
 	JSR 	L28:
L36: 	DEC 	1
L35: 	HLT 	

