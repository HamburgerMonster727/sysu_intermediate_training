第１步：下载ant  # 浏览器或者wget

apache-ant-1.10.0-bin.tar.gz
http://archive.apache.org/dist/ant/binaries/

第２步：解压 #不想用tar也可以手动解压

tar -zxvf apache-ant-1.10.0-bin.tar.gz

第３步：不要将ant复制或移动到~以外的目录，复制到有权限的目录（比如~/Desktop/ant）（云平台权限问题

mv apache-ant-1.10.0 ~/Desktop/ant

第４步：设置环境变量

gedit ~/.bashrc

添加：

export ANT_HOME=~/Desktop/ant
export PATH=${ANT_HOME}/bin:$PATH


第５步：使修改立即生效

source ~/.bashrc

第 6 步：测试

ant

如果显示
Buildfile：build.xml does not exist!
Build failed
则ant配置成功