## CA2 - Part 1: Virtualization with Vagrant - Technical Report
**Author:** Diana Guedes  
**Date:** 04/2025  
**Discipline:** DevOps  
**Program:** SWitCH DEV  
**Institution:** Instituto Superior de Engenharia do Porto (ISEP)


### Table of Contents

1. [Introduction](#1-introduction)
2. [Virtual Machine Setup](#2-virtual-machine-setup)
3. [Network Configuration and Services](#3-network-configuration-and-services)
4. [Cloning the Repository](#4-cloning-the-repository)
5. [Set Up Development Environment](#5-set-up-development-environment)
6. [Running the Spring Boot Tutorial Project](#6-running-the-spring-boot-tutorial-project)
7. [Running the gradle basic demo Project](#7-running-the-gradle-basic-demo-project-)
8. [Running the gradle_basic_demo Project – Part 2](#8-running-the-gradle_basic_demo-project--part-2)
9. [Conclusion](#9-conclusion)

---


### 1. Introduction

This report outlines the steps I followed to complete Part 1 of the CA2 assignment, focused on exploring virtualization through VirtualBox and UTM on an Ubuntu Server environment. The goal of this assignment was to gain hands-on experience with virtual machines and simulate real-world software development environments.

Throughout this report, I describe how I created and configured a VM, installed the necessary development tools, cloned and executed previous Java-based projects inside the virtual environment, and ensured communication between the VM and my host machine.

---

### 2. Virtual Machine Setup

To begin, I installed UTM from its official website and proceeded to create a new virtual machine using the graphical interface. I assigned a meaningful name, selected Linux as the OS type, and chose the appropriate Ubuntu version.

I allocated RAM and created a virtual hard disk with enough storage for development tools and projects. 

After installing , I configured the VM settings to match the requirements of the assignment. This included assigning **two network adapters**:
- Adapter 1 set to **NAT**, to allow internet access from within the VM.
- Adapter 2 configured as a **Host-Only Adapter** , to allow communication between the VM and the host machine.

---

### 3. Network Configuration and Services

With the VM ready, I moved on to setting up the network and essential services.

 I created a new host-only interface and defined the static IP address `192.168.56.5` for the second adapter (enp0s8), ensuring it fell within the host-only subnet.

After booting the VM, I updated the package index:

```bash
sudo apt update
```

I then installed useful networking tools:

```bash
sudo apt install net-tools
```

To assign the static IP, I edited the network config file:

```bash
sudo nano /etc/netplan/01-netcfg.yaml
```

To apply the changes:

```bash
sudo netplan apply
```

For remote access and secure file transfer, I installed and configured both **SSH** and **FTP**:

```bash
sudo apt install openssh-server
sudo nano /etc/ssh/sshd_config
# Enabled PasswordAuthentication
sudo service ssh restart
```

For FTP access:
```bash
sudo apt install vsftpd
sudo nano /etc/vsftpd.conf
# Enabled write_enable=YES
sudo service vsftpd restart
```

---

### 4. Cloning the Repository

To access my personal codebase within the virtual machine, I established a secure SSH connection between the VM and GitHub. This ensured that I could interact with my remote repository without needing to enter credentials each time.

I started by generating a new SSH key pair directly from the terminal in the Ubuntu Server VM:

```bash
ssh-keygen -t ed25519 -C "1241903@isep.ipp.pt"
```
![Keygen](images/SSHkeygen.png)

When prompted, I accepted the default file location. After the key pair was generated, I displayed the public key using:

```bash
cat ~/.ssh/id_ed25519.pub
```

![Public Key](images/PublicKey.png)

I copied the output and added it to my GitHub account by navigating to **Settings → SSH and GPG keys → New SSH key**. After pasting the key and saving, GitHub was able to recognize the VM as an authorized device.
Then verify the connection:

```bash
ssh -T git@github.com
```
![Connection](images/SHHWithGitHub.png)

With authentication set up, I proceeded to clone my repository into the VM using the SSH protocol:

```bash
git clone git@github.com:DianaGuedes19/devops-24-25-1241903.git
```
![Clonning](images/ClonningGitRepository.png)

This allowed me to securely pull the codebase into the development environment and continue working on the projects required for this assignment.

---

### 5. Set Up Development Environment

After confirming that the virtual machine had network access and SSH connectivity, I began setting up the development environment required to build and run the Java projects.

I started by updating the package lists and upgrading the installed packages to ensure the system was up-to-date:

```bash
sudo apt update
sudo apt upgrade
```

Then, I installed the essential development tools:

- **Git**, for version control:
  ```bash
  sudo apt install git
  ```

- **Java JDK and JRE (version 17)**, needed for compiling and running the projects:
  ```bash
  sudo apt install openjdk-17-jdk openjdk-17-jre
  ```

- **Maven**, used to manage dependencies in some of the projects:
  ```bash
  sudo apt install maven
  ```

To install **Gradle**, I manually downloaded and extracted version 8.6, as it was not available in the correct version through `apt`:

```bash
wget https://services.gradle.org/distributions/gradle-8.6-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-8.6-bin.zip
```

I then added Gradle to the system `PATH` by editing the `.bashrc` file:

```bash
echo "export GRADLE_HOME=/opt/gradle/gradle-8.6" >> ~/.bashrc
echo "export PATH=\$GRADLE_HOME/bin:\$PATH" >> ~/.bashrc
source ~/.bashrc
```

After completing the installations, I verified that all tools were properly installed and accessible via the terminal by checking their versions:

```bash
git --version
java --version
mvn --version
gradle --version
```

![Versions](images/versions.png)

This confirmed that the virtual machine was fully prepared to support Java development workflows.

---

### 6. Running the Spring Boot Tutorial Project

One of the initial tests involved running the Spring Boot tutorial project provided in earlier assignments. The objective was to build and execute this project entirely within the virtual machine configured in previous steps.

I navigated to the folder containing the Spring Boot project. Once inside the project directory, I launched the application using Maven:

```bash
./mvnw spring-boot:run
```
![MVN SpringBoot Run](images/mvnSpringBootRun.png)

After starting the application, I ensured that it was accessible from the host machine. To find the virtual machine’s IP address, I used the following command:

```bash
ifconfig
```

Using the VM’s static IP address (`192.168.56.5`), I was able to access the application from a browser on my Mac by visiting:

```
http://192.168.56.5:8080/
```

![FrontEnt](images/SpringBootResultWithIP.png)

The application loaded as expected, confirming that the backend service was operational and that the Spring Boot framework was correctly configured and running. I took a screenshot of the landing page to document the successful deployment and accessibility of the application.

---

### 7. Running the gradle basic demo Project 

This section focuses on the execution of the `gradle_basic_demo` project, which includes a simple client-server chat application. Due to the project’s graphical interface components, it required coordination between two environments: the virtual machine and the host machine.

Inside the VM, I navigated to the `gradle_basic_demo` directory and compiled the project using Gradle:

```bash
./gradlew build
```

![Gradle Build](images/gradleBuild.png)

The build completed successfully. However, because the VM was running a minimal version of Ubuntu Server without a graphical interface, it was not possible to launch the chat client from within the VM.

To overcome this, I cloned the same project repository on my host machine (macOS) and used it to run the client component of the application. Meanwhile, the server component remained running on the VM.

From the host machine, I opened two terminal windows and executed the following command in each to launch two independent client instances:

```bash
./gradlew runClient --args="192.168.56.5 59001"
```

![Gradle RunServer](images/gradleRunServerVM.png)

By specifying the VM's static IP address and the appropriate port, both client windows were able to establish a connection with the server running inside the VM. The chat application functioned correctly: messages sent from one client appeared in the other, confirming that the communication between client and server was successful.
!
[Gradle Chat](images/chatGradleAndVM.png)

---

### 8. Running the gradle_basic_demo Project – Part 2

In this section, I focused on building and executing an additional component of the `gradle_basic_demo` project within the virtual machine environment.

I navigated to the appropriate subdirectory and initiated the build process:

```bash
./gradlew build
```

Although the project compiled successfully, an initial attempt to run the application using:

```bash
./gradlew bootRun
```

Resulted in an error because the Gradle Wrapper could not download the required Gradle distribution due to the VM's lack of internet access.

To resolve this issue, I used the following command to regenerate the wrapper and make the necessary files available locally:

```bash
gradle wrapper
```

After that, I was able to execute the Spring Boot application successfully using:

```bash
./gradlew bootRun
```
![Gradle bootRun](images/gradleBootRun.png)

The server started correctly and was accessible from the host machine by navigating to:

```
http://192.168.56.5:8080/
```
![Gradle FrontEnd](images/gradleBootRunFrontEnd.png)

This confirmed that the Spring Boot component of the project was functional and properly deployed within the VM.

---

### 9. Conclusion

This technical report has documented the end-to-end setup and usage of a virtualized development environment for Class Assignment 2 – Part 1. The tasks completed included creating a virtual machine, configuring its network and essential services, installing development tools, and executing multiple Java-based projects.

Throughout the assignment, I gained practical experience with virtualization tools, specifically UTM on macOS, and successfully deployed and tested various applications within an Ubuntu Server environment. Running projects such as the Spring Boot tutorial and the `gradle_basic_demo` allowed me to simulate real-world deployment scenarios while reinforcing my understanding of system configuration and network communication between virtual and host machines.

During the execution of the `gradle_basic_demo` Spring Boot component, I encountered an issue where the Gradle Wrapper failed due to the VM not having internet access. I resolved this by generating the wrapper manually with `gradle wrapper`, which allowed me to run the application successfully within the VM and confirm it was reachable from the host.

Challenges such as configuring static IP addresses, enabling SSH/FTP access, and handling environment limitations were addressed effectively. These experiences contributed to a stronger grasp of the DevOps workflow in virtualized setups.

Overall, this assignment enhanced my ability to manage and troubleshoot development environments, reinforcing essential skills for real-world DevOps practices and my ongoing professional growth.

---

