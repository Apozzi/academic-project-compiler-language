.assembly extern mscorlib {}
.assembly _codigo_objeto{}
.module   _codigo_objeto.exe
.class public _UNICA{
.method static public void _principal() {
.entrypoint
.locals(int64 lado)
.locals(int64 area)
ldstr "maximo multiplo 50:"
call void [mscorlib]System.Console::Write(string)
call string [mscorlib]System.Console::ReadLine()
call int64 [mscorlib]System.Int64::Parse(string)
stloc lado
Loop1:
ldloc lado
conv.r8
ldc.i8 2
conv.r8
ceq
brfalse Loop2End
ldloc lado
conv.r8
ldc.i8 2
conv.r8
mul
conv.i8
stloc lado
br Loop3
Loop2End:
ldloc lado
conv.r8
ldc.i8 3
conv.r8
mul
conv.i8
stloc lado
Loop3:
ldloc lado
conv.r8
conv.i8
call void [mscorlib]System.Console::Write(int64)
call string [mscorlib]System.Console::ReadLine()
call int64 [mscorlib]System.Int64::Parse(string)
stloc lado
call string [mscorlib]System.Console::ReadLine()
call int64 [mscorlib]System.Int64::Parse(string)
stloc lado
ret
}
}
