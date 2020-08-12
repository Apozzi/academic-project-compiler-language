# academic-project-compiler-language
Lexical and Semantical compiler and IDE thats generate ildasm .NET assembly code and a executable.

![Screenshot](https://i.postimg.cc/7LfHR23s/2020-08-12-120631-1920x1080-scrot.png)

## Example of Executable code

```
def
var
palp, qtd, maiorQue, menorQue, resp : int
cont : bool
execute
palp := 0
qtd := 0
maiorQue := 0
menorQue := 1000
cont := true
resp := 357
(cont) whileTrue:
qtd := qtd + 1
println("Digite um número maior que ",maiorQue," e menor que ",menorQue,".")
input(palp)
(palp = resp) ifTrue:
cont := false
ifFalse:
(palp > maiorQue && palp < resp) ifTrue:
maiorQue := palp
ifFalse:
(palp > resp && palp < menorQue) ifTrue:
menorQue := palp
end
end
end
end
println("Você acertou o número em apenas ",qtd," tentativas")
```

I dont have any documentation about the language...
