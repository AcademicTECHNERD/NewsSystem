<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { updatePasswordService } from '@/api/user.js' // 假设你有这个API方法
import { useTokenStore } from '@/stores/token.js'

// 在 router/index.js 中创建并导出的 router 实例是一个单例，可以在应用中的任何地方直接引用。
//你不需要在每个文件中调用 useRouter() 来获得这个实例。
import { useRouter } from 'vue-router'

const tokenStore = useTokenStore();
const router = useRouter();

// 密码表单数据
const passwordForm = ref({
    old_pwd: '',  // 修改为 old_pwd
    new_pwd: '',  // 修改为 new_pwd
    re_pwd: ''    // 修改为 re_pwd
})

// 获取 token
const token = tokenStore.token;

// 密码验证规则
const passwordRules = {
    old_pwd: [
        { required: true, message: '请输入旧密码', trigger: 'blur' }
    ],
    new_pwd: [
        { required: true, message: '请输入新密码', trigger: 'blur' },
        {
            pattern: /^\S{6,20}$/,
            message: '新密码必须是6-20位的非空字符串',
            trigger: 'blur'
        }
    ],
    re_pwd: [
        { required: true, message: '请确认新密码', trigger: 'blur' },
        {
            validator: (rule, value, callback) => {
                if (value !== passwordForm.value.new_pwd) {
                    callback(new Error('两次输入的新密码不一致'))
                } else {
                    callback()
                }
            },
            trigger: 'blur'
        }
    ]
}

// 更新密码
const updatePassword = async () => {
    try {
        let result = await updatePasswordService(passwordForm.value); 
        ElMessage.success(result.msg ? result.msg : '密码修改成功');

        // 清空密码表单
        passwordForm.value.old_pwd = '';
        passwordForm.value.new_pwd = '';
        passwordForm.value.re_pwd = '';
        router.push('/');
    } catch (error) {
        console.error("Error during password update:", error);
        console.error("Error response:", error.response);
        ElMessage.error(error.response?.data?.message || '密码修改失败');
    }
};
</script>

<style scoped>
.page-container {
    padding: 20px;
}

.header {
    font-size: 16px;
    font-weight: bold;
}
</style>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>修改密码</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form
                    :model="passwordForm"
                    :rules="passwordRules"
                    ref="passwordFormRef"
                    label-width="100px"
                    size="large"
                >
                    <el-form-item label="旧密码" prop="old_pwd">
                        <el-input type="password" v-model="passwordForm.old_pwd" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="new_pwd">
                        <el-input type="password" v-model="passwordForm.new_pwd" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="确认新密码" prop="re_pwd">
                        <el-input type="password" v-model="passwordForm.re_pwd" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="updatePassword">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>
