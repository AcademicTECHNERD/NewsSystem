<script setup>
import { ref, onMounted } from 'vue';
import { articleListService } from '@/api/article.js';
import { useRouter } from 'vue-router';

// 数据模型
const articles = ref([]);
const selectedArticle = ref(null);
const router = useRouter();

// 获取文章列表
const fetchArticles = async () => {
    try {
        const result = await articleListService({ pageNum: 1, pageSize: 100 });
        console.log('Fetched articles:', result.data.items); // 输出获取到的文章
        articles.value = result.data.items;
    } catch (error) {
        console.error('Failed to fetch articles:', error);
    }
};

// 检查用户身份
const username = localStorage.getItem('username');
const isAdmin = username === 'administrator';

if (isAdmin) {
    // 如果是管理员，重定向到文章管理页面
    router.push('/article/manage');
} else {
    // 初始化数据
    onMounted(fetchArticles);
}

// 选择文章的函数
const selectArticle = (article) => {
    selectedArticle.value = article; // 选择文章
};

// 退出登录函数
const logout = () => {
    localStorage.removeItem('username'); // 清除用户信息
    localStorage.removeItem('token'); // 假设你用 token 进行身份验证
    router.push('/login'); // 重定向到登录页面
};
</script>

<template>
    <div class="page-container">
        <aside class="article-list">
            <h2>文章列表</h2>
            <ul>
                <li v-for="article in articles" :key="article.id" @click="selectArticle(article)"
                    :class="['article-item', { selected: selectedArticle === article }]">
                    {{ article.title }}
                </li>
            </ul>
        </aside>

        <main class="article-content" v-if="selectedArticle">
            <h2>{{ selectedArticle.title }}</h2>
            <p v-html="selectedArticle.content"></p>
        </main>

        <main class="article-content" v-else>
            <p>请选择一篇文章进行阅读。</p>
        </main>

        <button @click="logout" class="logout-button">退出登录</button> <!-- 退出登录按钮 -->
    </div>
</template>

<style scoped>
.page-container {
    display: flex;
    height: 100vh;
    position: relative; /* 为了让绝对定位相对这个容器 */
}

.article-list {
    width: 250px;
    background-color: #f9f9f9; /* 侧边栏背景色 */
    padding: 10px;
    border-right: 1px solid #e0e0e0; /* 侧边栏右边框 */
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* 侧边栏阴影 */
}

.article-item {
    cursor: pointer;
    padding: 10px;
    border-radius: 4px;
    transition: background-color 0.3s;
    list-style: none; /* 去除序点 */
    text-align: left; /* 左对齐 */
}

.article-item:hover {
    background-color: #ffcccb; /* 悬停效果粉色 */
}

.article-item.selected {
    background-color: #ffcccb; /* 选中后保持粉色 */
}

.article-content {
    flex: 1;
    padding: 20px; /* 内容区内边距 */
    background-color: #fff; /* 内容区背景色 */
    overflow-y: auto; /* 滚动条 */
}

/* 退出登录按钮样式 */
.logout-button {
    position: absolute; /* 绝对定位 */
    bottom: 20px; /* 距离底部20像素 */
    left: 20px; /* 距离左侧20像素 */
    padding: 10px 15px; /* 增加内边距 */
    background-color: #f56c6c; /* 按钮背景色 */
    color: white; /* 按钮字体颜色 */
    border: none; /* 去掉边框 */
    border-radius: 4px; /* 圆角 */
    cursor: pointer; /* 鼠标悬停时显示手指 */
    font-size: 16px; /* 字体大小 */
    transition: background-color 0.3s, transform 0.3s; /* 动画效果 */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
}

.logout-button:hover {
    background-color: #f44336; /* 悬停时颜色变化 */
    transform: scale(1.05); /* 悬停时放大效果 */
}

h2 {
    color: #333; /* 文字颜色 */
}
</style>
