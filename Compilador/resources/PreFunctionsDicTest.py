b = (0,4,3,7+5,8,10,9==9,54)
c = {"uno":b,"dos":2,"tres":3}

print c
print ""

print "Resultado de has_key(uno)"
a = c.has_key("uno")
print a
print ""

print "Resultado de pop(uno)"
c.pop("uno")
print c
print ""

print "Resultado de has_key(uno)"
d = c.has_key("uno")
print d
print ""

print "Resultado de items()"
e = c.items()
print e
print ""

print "Resultado de keys()"
f = c.keys()
print f
print ""


print "Resultado de values()"
g = c.values()
print g
print ""

print "Resultado de values(3)"
c.values(3)
