# bassy
quality control (QC) platform

================


<!-- MarkdownTOC -->

- [Bassy 功能概述](#功能概述)  
- [系统详述](#系统详述)
    - [功能概览](#功能概览)
    - [我的任务](#我的任务)
    - [项目管理](#项目管理)
        - [贯穿产品项目测试阶段的生命周期](#vera-系统高可用)
        - [项目测试文档相关](#项目测试文档相关)
        - [内部项目运用](#内部项目运用)
    - [测试用例](#测试用例)
    - [智库](#智库)
    - [资源情况](#资源情况)
    - [统计分析](#统计分析)
    - [资产管理](#资产管理)
    - [配置管理](#配置管理)
- [安装部署](#安装部署) 
<!-- /MarkdownTOC -->

<h1 name="功能概述">功能概述</h1>


- 管理项目立项后，从建测试计划-》测试任务拆分-》测试执行-》灰发-》上线的全过程
- 项目管理有许多能提升效能以及收集过程数据的亮点功能，例如：  
      -1、测试计划自动拆分成所有测试任务且可以同步禅道  
      -2、过程文档统一管理  
      -3、过程异常收集及统计  
      -4、人员工作量热力图采集  
      -5、项目日报、测试日报等自动生成
- 自建测试用例模块，自建项目用例与基线用例的更新规则，解决了需求迭代速度过快，导致测试用例没时间更新等导致的基线测试用例不完整的问题，同时可以在线xmind编写及执行用例，并统计
- 鼓励测试人员建立良好的学习和分享氛围，建立智库模块
- 测试团队内部的所有内部资产的统一线上管理
<h1 name="系统详述">系统详述</h1>

<h1 name="安装部署">安装部署</h1>
1.在bassy-mapper模块下，存放有数据库表和数据初始化sql文件，请先初始化数据库信息。  
2.在bassy-web模块下，配置数据库和redis的连接配置。  
3.在bin目录下，执行install.sh脚本，会自动打包并启动服务。

