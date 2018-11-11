# AI experiment / GDX library testing / project abandoned

# SnakePit

![Image](https://zagorskidev.files.wordpress.com/2017/10/zrzut-ekranu-z-2017-10-18-17-15-22.png)

# How is it done?

Build in libGDX. Snakes use some AI methods. They look for food (yellow dots) and choose target considering distance and overload – if one food object is targeted by more snakes it’s less attractive for others. If snake can’t go to target due to barriers (other snakes) it choose other target or just move. If no food object is targeted, snakes decide if more free space they have on front, left or right side and move in choosen direction. Snakes avoid collisions, but sometimes (especially if they are long) they stuck in dead ends.

Of course you can control one snake in the pit. 😉

# How does it look like?

You can watch short video of application running.

[![Video](https://img.youtube.com/vi/5W-C0imSvto/0.jpg)](https://youtu.be/5W-C0imSvto)

# How can I run it?

You can play this game in web browser. You are controlling green snake with W A S D keys on keyboard.
http://zagorskidev.cba.pl/

# How can it be improved?

There could be added some metagame (points, levels etc.) to improve playability. Now it’s just demo of snake control methods.
