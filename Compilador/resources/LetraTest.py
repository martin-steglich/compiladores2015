print "Hola Mundo"

# esto es una cadena
c = "Hola Mundo"

# y esto es un entero
e = 23

# podemos comprobarlo con la funcion type
print type(c)
print type(e)

# type(entero) devolveria int
entero = 23
print type(entero)

# type(enter) devolveria long
entero = 23L
print type(entero)

real = 0.2703
print type(real)

r = 3 + 2
print r

r = 4 - 7
print r

r = -7
print r

r = 2 * 6
print r

r = 2 ** 6
print r

r = 3.5/2
print r

r = 3.5//2
print r

r = 7%2
print r

r = 3 + 6.0
print type(r)

r = 3 & 2
print r

r = 3 | 2
print r

r = 3^2
print r

r = ~3
print r

r = 3 << 1
print r 

r = 3 >> 1
print r

triple = """primera linea
				esto se vera en otra linea"""
print triple

a="uno"
b="dos"
c = a + b # c es "unodos"
print c

c = a * 3 # c es "unounouno"
print c

r = True and False
print r

r = True or False
print r

r = not True
print r

r = 5 == 3
print r

r = 5 != 3
print r

r = 5 < 3
print r

r = 5 > 3
print r

r = 5 <= 5
print r

r = 5 >= 3
print r

l = [22, True, "una lista", [1,2]]
print type(l)

l = [11, False]
mi_var = l[0]
print mi_var

l = [99, True, "una lista", [1,2]]
mi_var = l[0:2]
print mi_var

mi_var = l[0:4:2]
print mi_var

l = [99, True, "una lista"]
mi_var = l[1:]
print mi_var

mi_var = l[:2]
print mi_var

mi_var = l[:]
print mi_var

mi_var = l[::2]
print mi_var

l=[99, True, "una lista", [1,2]]
print l
l[0:2] = [0,1]
print l

d = { 
	  "Love Actually" : "Richard Curtis",
	  "Kill Bill": "Tarantino",
	  "Amelie": "Jean-Pierre Jeunet"
}
print d

print d["Love Actually"]
d["Kill Bill"] = "Quentin Tarantino"
print d["Kill Bill"]
print d

t = (1,2,True, "python")
print t

t = (1)
print type(t)

t = (1,)
print type(t)

t = (1,2,True, "python")
mi_var = t[0]
print mi_var

mi_var = t[0:2]
print mi_var

fav = "mostrar_saludo"
if fav == "mostrar_saludo":
	print "Hola que tal"
	print "Bienvenidos"

if fav != "mostrar_saludo":
	print "Hola que tal"
	print "Bienvenidos"
else:
	print "Chau chau!"

edad = 0
while edad < 18:
	edad = edad + 1
	print "Felicidades, tienes " + str(edad)

print "Felicidades, ya es mayor de edad!"

while True:
	entrada = raw_input("> ")
	if entrada == "adios":
		break
	else:
		print entrada

edad = 0
while edad < 18:
	edad = edad + 1
	if edad % 2 == 0:
		continue
	print "Felicidades, tienes " + str(edad)

secuencia = ["uno", "dos", "tres"]
for elemento in secuencia:
	print elemento

def sumar(x,y):
	return x + y

print sumar(3,2)
print sumar(x=3, y=2)

nombre = raw_input("Como se llama? ")

print "Hola que tal!, " + nombre
