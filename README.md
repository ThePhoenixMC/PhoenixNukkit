<h1 align="center">
  <br>
  <img src="https://avatars0.githubusercontent.com/u/35197080?s=400&u=289914ca504d80656a3cdb9646342c76666e2c0b&v=4" alt="Phoenix" width="200"><img src="https://raw.githubusercontent.com/NukkitX/Nukkit/master/.github/images/logo.png" alt="Phoenix" width="200"></a>
  <br>
  Phoenix Nukkit
  <br>
</h1>

A Nukkit implementation of the Phoenix API.

 [![Build Status](https://travis-ci.org/ThePhoenixMC/PhoenixNukkit.svg?branch=master)](https://travis-ci.org/ThePhoenixMC/PhoenixNukkit)
[![Gitter](https://camo.githubusercontent.com/da2edb525cde1455a622c58c0effc3a90b9a181c/68747470733a2f2f6261646765732e6769747465722e696d2f4a6f696e253230436861742e737667)](https://gitter.im/ThePhoenixMC)
[![GitHub forks](https://img.shields.io/github/forks/ThePhoenixMC/PhoenixNukkit.svg?style=flat-square&label=Fork)](https://github.com/ThePhoenixMC/PhoenixAPI/fork)
![GitHub stars](https://img.shields.io/github/stars/ThePhoenixMC/PhoenixNukkit.svg?style=flat-square&label=Stars)
[![LICENSE](https://img.shields.io/github/license/ThePhoenixMC/PhoenixNukkit.svg)](LICENSE)



# Requirements

-  Java 1.8 or higher
-  Maven

# Cloning
The following steps will ensure your project is cloned properly.

 ```
git clone  --recursive https://github.com/ThePhoenixMC/PhoenixNukkit.git
```

## Updating 
The following steps will update your clone with the official repo.

```
git pull
git submodule update --recursive
```
# Building

1.  Cloning project
2. Building:
```
./mvnw clean install
```
You can find the compiled JAR files in `PhoenixNukkit/target` directory.

# Running

After building the project, drop `PhoenixNukkit-x.x.x-x-x.x-x.jar' to your server's plugin directory, and then restart your server.

-  You can put your Phoenix Module to 'modules' directory.
- The configuration files of the modules is under 'config' directory.


# Contributing

See [CONTRIBUTING](CONTRIBUTING.md).

# License

This project is licensed under the [MIT License](LICENSE).
