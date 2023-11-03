# DUXAPP
duxapp是基于Taro开发的模块化的前端框架，同时支持小程序、H5、ReactNative。
## 安装

```bash
npx duxapp-cli app init 项目名称
```

项目初始化后将会自动安装依赖，安装完成就可以打开项目进行开发了

## 使用
### 基本命令

```bash

# duxapp自定义命令参数：在上面的命令的基础上新增参数
# 调试小程序端 打包商城模块
yarn dev weapp --app=duxapp
# 调试小程序端 打包基础模块
yarn dev weapp --app=duxui
```
需要注意的是当同时调试多个端时，需要输入一致的`--app`参数

## 模块简介
duxapp是模块化的，模块之间还有相互依赖关系，src目录下的每个文件夹即是一个模块
### 模块目录结构
|  路径   | 说明 |
|  ----  | ----  |
| configs | 里面放项目配置文件 |
| src | 模块根目录 |
| --base  | 模块目录 |
| ---- components  | 模块组件目录 |
| ------ ......  | 导出模块的组件 |
| ---- config  | 配置 |
| ------ route.js  | 路由配置文件-模块必须的文件 |
| ------ ......  | 其他配置文件 |
| ---- utils  | 工具目录 |
| ------ ......  | 导出工具函数 |
| ---- update  | 项目兼容配置 |
| ---- app.js  | 模块入口文件 |
| ---- app.json  | 模块配置文件 定义模块名称、版本、依赖关系 |
| ---- index.js  | 模块出口文件，可以导出组件和方法提供给其他模块使用 |
| ---- ......  | 其他文件夹表示页面 |
| -- app.js  | 全局入口文件 自动生成 |
| -- app.scss  | 全局样式 自动生成 |
| -- app.config.js  | taro配置 自动生成 |
| -- index.html  | h5端首页文件 自动生成 |

### 模块命令

```bash
# 创建一个项目
yarn duxapp app init 项目名称
# 添加一个模块
yarn duxapp app add 模块名称
# 创建一个模块
yarn duxapp app create 模块名称
# 发布模块
yarn duxapp app publish 模块名称 是否发布依赖的模块
```
### 模块介绍
- 模块的依赖关系非常有用，可以在打包的时候自动寻找需要的模块，也可以在安装模块的时候自动寻找需要的模块
- 模块依赖请勿循环依赖 例如 A->B->A，或者A->B->C->A
- 如果A模块依赖于B模块，则可以在A模块中放心的导入B模块的组件和函数，反之没有依赖的模块不要导入他的任何东西，否则打包将会报错
- 一般来说新的模块至少需要依赖于base模块，因为base模块里面包含了很多基础工具和函数

### 入口文件

index.js是每个模块的入口文件，这个文件将会被默认执行，此文件需要导出一个默认对象，如下

```js
// 可以在此处执行一些要初始化的东西
import { app } from '@/base/utils'
app.register('duxshop')

export default {
  // 启动配置 位于duxapp.js文件中的option配置
  option: option => {
    setAppConfig(option.app || {})
  },
  // useLaunch
  launch: () => {
    route.init()
  },
  // useShow
  show: () => { },
  // useHide
  hide: () => { },
  // useEffect
  effect: async () => {
    startHide()
  }
}
```
