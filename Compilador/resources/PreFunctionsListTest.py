a = [1,2,2,2,2,2,3,4,5]
print a 
print ""
b = a.index(2)
c = a.index(2, 4)
print "resultado de index(2):"
print b
print ""
print "resultado de index(2,4):"
print c
print ""
a.append("mas")
print "resultado de append(mas):"
print a
print ""
e = a.count(2)
print "resultado de count(2):"
print  e
print ""
hola = ["esto","es","agregado"]
a.extend(hola)
print "resultado de extend([esto,es,agregado]):"
print  a
print ""
a.insert(2,"dos")
print "resultado de insert(dos):"
print  a
print ""
a.pop(13)
print "resultado de pop(13):"
print  a
print ""
f = a.size()
print "resultado de size():"
print  f