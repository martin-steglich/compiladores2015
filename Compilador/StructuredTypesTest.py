a = [0,'hola',3,"pez",8,10,2,54]
b = (0,4,3,7+5,8,10,9==9,54)
c = {"uno":b,"dos":2,"tres":3}

print "map"
print c

print "lista"
print a[-3]
print a[3]
print a[0:2]
print a[0:3:2]
print a[1:]
print a[:3]
print a[:]
print a[::2]
print a[:4:1]
print a[4::3]
a[0] = "ok"
print a[:]

print "tupla"
print b[-3]
print b[3]
print b[0:2]
print b[0:3:2]
print b[1:]
print b[:3]
print b[:]
print b[::2]
print b[:4:1]
print b[4::3]
b[2] = '''nooo'''
