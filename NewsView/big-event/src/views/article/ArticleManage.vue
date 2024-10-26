<script setup>
import {
    Edit,
    Delete,
    Plus
} from '@element-plus/icons-vue';
import { ref } from 'vue';
import { articleCategoryListService, articleListService, articleAddService, articleUpdateService, articleDeleteService } from '@/api/article.js';
import { ElMessage } from 'element-plus';
import { useTokenStore } from '@/stores/token.js';
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';

// 数据模型
const categorys = ref([]);
const articles = ref([]);
const categoryId = ref('');
const state = ref('');
const pageNum = ref(1);
const total = ref(0);
const pageSize = ref(3);
const visibleDrawer = ref(false);
const articleModel = ref({
    title: '',
    categoryId: '',
    coverImg: '',
    content: '',
    state: ''
});

// 导入token
const tokenStore = useTokenStore();

// 获取文章分类
const articleCategoryList = async () => {
    const result = await articleCategoryListService();
    categorys.value = result.data;
};

// 获取文章列表
const articleList = async () => {
    const params = {
        pageNum: pageNum.value,
        pageSize: pageSize.value,
        categoryId: categoryId.value || null,
        state: state.value || null
    };
    const result = await articleListService(params);
    total.value = result.data.total;
    console.log('Fetched articles:', result.data.items); // 输出获取到的文章
    articles.value = result.data.items.map(article => {
        const category = categorys.value.find(c => c.id === article.categoryId);
        return { ...article, categoryName: category ? category.categoryName : '' };
    });
};

// 添加文章
const addArticle = async (clickState) => {
    articleModel.value.state = clickState;
    const result = await articleAddService(articleModel.value);
    ElMessage.success(result.msg || '添加成功');
    visibleDrawer.value = false;
    articleList();
};

// 编辑文章
const editArticle = async (row) => {
    articleModel.value = { ...row };
    visibleDrawer.value = true;
};

// 删除文章
const deleteArticle = async (row) => {
    const result = await articleDeleteService(row.id);
    ElMessage.success(result.msg || '删除成功');
    articleList();
};

// 上传成功回调
const uploadSuccess = (result) => {
    articleModel.value.coverImg = result.data;
};

// 页码变化
const onSizeChange = (size) => {
    pageSize.value = size;
    articleList();
};

// 当前页码变化
const onCurrentChange = (num) => {
    pageNum.value = num;
    articleList();
};

// 初始化数据
articleCategoryList();
articleList();
</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>文章管理</span>
                <div class="extra">
                    <el-button type="primary" @click="visibleDrawer = true">添加文章</el-button>
                </div>
            </div>
        </template>
        <!-- 搜索表单 -->
        <el-form inline>
            <el-form-item label="文章分类：">
                <el-select placeholder="请选择" v-model="categoryId">
                    <el-option v-for="c in categorys" :key="c.id" :label="c.categoryName" :value="c.id"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="发布状态：">
                <el-select placeholder="请选择" v-model="state">
                    <el-option label="已发布" value="已发布"></el-option>
                    <el-option label="草稿" value="草稿"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="articleList">搜索</el-button>
                <el-button @click="categoryId = ''; state = ''">重置</el-button>
            </el-form-item>
        </el-form>
        <!-- 文章列表 -->
        <el-table :data="articles" style="width: 100%">
            <el-table-column label="文章标题" prop="title" width="400"></el-table-column>
            <el-table-column label="分类" prop="categoryName"></el-table-column>
            <el-table-column label="发表时间" prop="createTime"></el-table-column>
            <el-table-column label="状态" prop="state"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="editArticle(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteArticle(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
        <!-- 分页条 -->
        <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize" :page-sizes="[3, 5, 10, 15]"
            layout="jumper, total, sizes, prev, pager, next" background :total="total" @size-change="onSizeChange"
            @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end" />
        <!-- 抽屉 -->
        <el-drawer v-model="visibleDrawer" title="添加文章" direction="rtl" size="50%">
            <el-form :model="articleModel" label-width="100px">
                <el-form-item label="文章标题">
                    <el-input v-model="articleModel.title" placeholder="请输入标题"></el-input>
                </el-form-item>
                <el-form-item label="文章分类">
                    <el-select placeholder="请选择" v-model="articleModel.categoryId">
                        <el-option v-for="c in categorys" :key="c.id" :label="c.categoryName" :value="c.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="文章封面">
                    <el-upload class="avatar-uploader" :auto-upload="true" :show-file-list="false" action="/api/upload"
                        name="file" :headers="{ 'Authorization': tokenStore.token }" :on-success="uploadSuccess">
                        <img v-if="articleModel.coverImg" :src="articleModel.coverImg" class="avatar" />
                        <el-icon v-else class="avatar-uploader-icon">
                            <Plus />
                        </el-icon>
                    </el-upload>
                </el-form-item>
                <el-form-item label="文章内容">
                    <div class="editor">
                        <quill-editor theme="snow" v-model:content="articleModel.content" contentType="html"></quill-editor>
                    </div>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="addArticle('已发布')">发布</el-button>
                    <el-button type="info" @click="addArticle('草稿')">草稿</el-button>
                </el-form-item>
            </el-form>
        </el-drawer>
    </el-card>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}

/* 抽屉样式 */
.avatar-uploader {
    :deep() {
        .avatar {
            width: 178px;
            height: 178px;
            display: block;
        }

        .el-upload {
            border: 1px dashed var(--el-border-color);
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
            transition: var(--el-transition-duration-fast);
        }

        .el-upload:hover {
            border-color: var(--el-color-primary);
        }

        .el-icon.avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 178px;
            height: 178px;
            text-align: center;
        }
    }
}

.editor {
    width: 100%;

    :deep(.ql-editor) {
        min-height: 200px;
    }
}
</style>
