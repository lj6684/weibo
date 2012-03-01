SELECT m.mid, m.content, m.img, m.create_time, u.uid, u.nick_name, u.head_img
FROM message m, user u 
WHERE m.uid=u.uid and (u.uid=? or u.uid in (SELECT tid FROM contact WHERE uid=?))

SELECT m.mid, m.content, m.img, m.create_time, u.uid, u.nick_name, u.head_img
FROM message m
NATURAL JOIN user u
WHERE u.uid=? or u.uid in (SELECT tid FROM contact WHERE uid=?)