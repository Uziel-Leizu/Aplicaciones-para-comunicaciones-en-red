#include <stdio.h>
#include <dos.h>

struct WORDREGS{
	unsigned int ax,bx,cx,dx,si,di,cflag,flags;
};
struct BYTEREGS{
	unsigned char al,ah,bl,bh,cl,ch,dl,dh;
};
union REGS{
	struct WORDREGS x;
	struct BYTEREGS h;
};

void main(){
	union REGS p;
	p.x.ax=1;
	int86(0x33,&p,&p);
}
