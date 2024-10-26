<script setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import useUserInfoStore from '@/stores/userInfo.js';
import { useTokenStore } from '@/stores/token.js';
import { userInfoService } from '@/api/user.js'; // 确保导入用户信息服务

const tokenStore = useTokenStore();
const userInfoStore = useUserInfoStore();
const route = useRoute();
const router = useRouter();
const isNewsPage = ref(false);

// 监听路由变化
watch(() => route.path, (newPath) => {
    isNewsPage.value = newPath === '/news';
});

// 获取用户信息
const getUserInfo = async () => {
    let result = await userInfoService();
    userInfoStore.setInfo(result.data);
};

// 下拉菜单命令处理函数
const handleCommand = (command) => {
    switch (command) {
        case 'info':
            // 导航到基本资料页面
            router.push('/user/info');
            break;
        case 'avatar':
            // 导航到更换头像页面
            router.push('/user/avatar');
            break;
        case 'resetPassword':
            // 导航到重置密码页面
            router.push('/user/resetPassword');
            break;
        case 'logout':
            // 处理退出登录
            logout();
            break;
        default:
            break;
    }
};

// 退出登录函数
const logout = () => {
    tokenStore.removeToken(); // 清除用户 token
    router.push('/login'); // 重定向到登录页面
};

// 初始化用户信息
getUserInfo();
</script>

<template>
    <el-container class="layout-container">
        <!-- 左侧菜单 -->
        <el-aside v-if="!isNewsPage" width="250px">
            <div class="el-aside__logo"></div>
            <el-menu background-color="#232323" text-color="#fff" active-text-color="#ffd04b" router class="menu">
                <el-menu-item index="/article/category" class="menu-item">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>文章分类</span>
                </el-menu-item>
                <el-menu-item index="/article/manage" class="menu-item">
                    <el-icon>
                        <Promotion />
                    </el-icon>
                    <span>文章管理</span>
                </el-menu-item>
                <el-sub-menu>
                    <template #title>
                        <el-icon>
                            <UserFilled />
                        </el-icon>
                        <span>个人中心</span>
                    </template>
                    <el-menu-item index="/user/info" class="menu-item">
                        <el-icon>
                            <User />
                        </el-icon>
                        <span>基本资料</span>
                    </el-menu-item>
                    <el-menu-item index="/user/avatar" class="menu-item">
                        <el-icon>
                            <Crop />
                        </el-icon>
                        <span>更换头像</span>
                    </el-menu-item>
                    <el-menu-item index="/user/resetPassword" class="menu-item">
                        <el-icon>
                            <EditPen />
                        </el-icon>
                        <span>重置密码</span>
                    </el-menu-item>
                </el-sub-menu>
            </el-menu>
        </el-aside>

        <!-- 右侧主区域 -->
        <el-container>
            <el-header class="el-header">
                <div>程序员：<strong>{{ userInfoStore.info.nickname }}</strong></div>
                <el-dropdown placement="bottom-end" @command="handleCommand">
                    <span class="el-dropdown__box">
                        <el-avatar :src="userInfoStore.info.userPic ? userInfoStore.info.userPic : avatar" />
                        <el-icon>
                            <CaretBottom />
                        </el-icon>
                    </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item command="info" :icon="User">基本资料</el-dropdown-item>
                            <el-dropdown-item command="avatar" :icon="Crop">更换头像</el-dropdown-item>
                            <el-dropdown-item command="resetPassword" :icon="EditPen">重置密码</el-dropdown-item>
                            <el-dropdown-item command="logout" :icon="SwitchButton">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </el-header>

            <el-main>
                <router-view @changePage="handlePageChange"></router-view>
            </el-main>

            <el-footer>新闻汇总论坛 ©2024 Created by 程序员~星</el-footer>
        </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
    display: flex;
    height: 100vh;

    .el-aside {
        background-color: #232323;
        padding: 10px 0;
    }

    .el-aside__logo {
        height: 120px;
        background: url('@/assets/nnutcLOGO.jpg') no-repeat center / 120px auto;
    }

    .menu {
        .menu-item {
            display: flex;
            align-items: center;
            padding: 15px 20px;
            transition: background-color 0.3s;

            &:hover {
                background-color: #444; /* 悬停背景色 */
            }

            &.is-active {
                background-color: #ffd04b; /* 选中项背景色 */
                color: #000; /* 选中项文字颜色 */
            }

            el-icon {
                margin-right: 10px;
            }
        }
    }

    .el-header {
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 20px;
    }

    .el-footer {
        text-align: center;
        font-size: 14px;
        color: #666;
        padding: 10px 0;
    }
}
</style>
