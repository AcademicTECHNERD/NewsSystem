import { createRouter, createWebHistory } from 'vue-router';

// 导入组件
import LoginVue from '@/views/Login.vue';
import LayoutVue from '@/views/Layout.vue';
import NewsVue from '@/views/News.vue';
import ArticleCategoryVue from '@/views/article/ArticleCategory.vue';
import ArticleManageVue from '@/views/article/ArticleManage.vue';
import UserAvatarVue from '@/views/user/UserAvatar.vue';
import UserInfoVue from '@/views/user/UserInfo.vue';
import UserResetPasswordVue from '@/views/user/UserResetPassword.vue';
import { useTokenStore } from '@/stores/token.js'; // 根据实际路径调整

// 创建路由关系
const routes = [
    { path: '/login', component: LoginVue },
    {
        path: '/',
        component: LayoutVue,
        redirect: '/article/manage',
        children: [
            { path: '/article/category', component: ArticleCategoryVue },
            { path: '/article/manage', component: ArticleManageVue },
            { path: '/user/info', component: UserInfoVue },
            { path: '/user/avatar', component: UserAvatarVue },
            { path: '/user/resetPassword', component: UserResetPasswordVue }
        ]
    },
    { path: '/news', component: NewsVue } // 确保 news.vue 在这里
];

// 创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes
});

// 导航守卫，控制访问权限
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('token');
    const username = localStorage.getItem('username');
    const isAdmin = username === 'administrator';

    console.log('Current token:', token);
    console.log('Username:', username);
    console.log('Is Admin:', isAdmin);
    console.log('Navigating from:', from.path, 'to:', to.path);

    if (to.meta.requiresAuth && !token) {
        next('/login');
    } else if (!token && to.path !== '/login') {
        next('/login');
    } else if (isAdmin && to.path === '/news') {
        next('/article/manage');
    } else if (!isAdmin && (to.path.startsWith('/article') || to.path.startsWith('/user'))) {
        next('/news');
    } else {
        next();
    }
});


// 导出路由
export default router;
