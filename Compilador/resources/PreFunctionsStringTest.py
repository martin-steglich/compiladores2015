s = "tres tristes tigres comen trigo en un trigal"
print s

print "Resultado de count(t)"
a = s.count('t')
print a
print ""

print "Resultado de count(b)"
b = s.count('b')
print b
print ""

print "Resultado de find(tri)"
c = s.find('tri')
print c
print ""

print "Resultado de find(tri,18)"
d = s.find('tri',18)
print d
print ""

print "Resultado de split(comen)"
e = s.split("comen")
print e
print ""

print "Resultado de split(hola)"
f = s.split("hola")
print f
print ""

print "Resultado de replace(tristes,felices)"
g = s.replace('tristes','felices')
print g
print ""

print "Resultado de length()"
h = s.length()
print h
print ""

print "Resultado de join(-)"
str = "-"
fo =  s.join( str )
print fo