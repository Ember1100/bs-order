Skip to content
Search or jump to…
Pull requests
Issues
Marketplace
Explore
 
@wujy4010 
Teskinfly
/
XiaXiaOOS-ssm-vue-
Public
Code
Issues
Pull requests
42
Actions
Projects
Security
Insights
XiaXiaOOS-ssm-vue-/XiaxiaOOS_Front/xiaxiaoos_front_admin/src/components/user/userManage.vue

teskinfly 优化查询
Latest commit 9d691bd on 20 Dec 2020
 History
 1 contributor
259 lines (259 sloc)  7.87 KB

<template>
  <div class="container">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="10">
          <el-input
            placeholder="请输入内容"
            class="input-with-select"
            v-model="tableData.query"
            clearable
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getUsers()"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button type="primary" @click="showAddDialog">添加用户</el-button>
          <el-dialog
            title="填写用户信息"
            :visible.sync="dialogVisible"
            width="50%"
            :before-close="handleClose"
          >
            <!-- <span>这是一段信息</span> -->
            <span slot="footer" class="dialog-footer">
              <el-form
                :label-position="labelPosition"
                label-width="80px"
                :model="user"
              >
                <el-form-item label="名称">
                  <el-input v-model="user.uname"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                  <el-input v-model="user.upwd"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                  <el-input v-model="user.uaddress"></el-input>
                </el-form-item>
                <el-form-item label="电话号码">
                  <el-input v-model="user.upn"></el-input>
                </el-form-item>
              </el-form>
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="submitUserInfo()"
                >确 定</el-button
              >
            </span>
          </el-dialog>
          <!-- edit -->
          <el-dialog
            title="编辑用户信息"
            :visible.sync="editDialogVisible"
            width="50%"
            :before-close="handleClose"
          >
            <span slot="footer" class="dialog-footer">
              <el-form
                :label-position="labelPosition"
                label-width="80px"
                :model="user"
              >
                <el-form-item label="名称">
                  <el-input v-model="user.uname"></el-input>
                </el-form-item>
                <!-- <el-form-item label="密码">
                  <el-input v-model="user.upwd"></el-input>
                </el-form-item> -->
                <el-form-item label="地址">
                  <el-input v-model="user.uaddress"></el-input>
                </el-form-item>
                <el-form-item label="电话号码">
                  <el-input v-model="user.upn"></el-input>
                </el-form-item>
              </el-form>
              <el-button @click="editDialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="updateUserInfo()"
                >确 定</el-button
              >
            </span>
          </el-dialog>
        </el-col>
      </el-row>
      <el-table :data="userList" style="width: 100%" border>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column
          prop="uname"
          label="姓名"
          width="200"
        ></el-table-column>
        <el-table-column prop="uaddress" label="地址" width="350">
        </el-table-column>
        <el-table-column prop="upn" label="电话号码" width="250">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="showEditDialog(scope.row)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              @click="delUser(scope.row.uid)"
              circle
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="tableData.pageNum"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="tableData.total"
        layout="total, sizes, prev, pager, next, jumper"
        :total="10"
      >
      </el-pagination>
    </el-card>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tableData: {
        total: 5,
        pageNum: 1,
        query: "",
      },
      userList: [],
      dialogVisible: false,
      editDialogVisible: false,
      user: {uid: "", uname: "", upwd: "", uaddress: "", uphone: "" },
      labelPosition: "right",
    };
  },
  created() {
    this.getListUsers();
  },
  methods: {
    showEditDialog(row) {
      this.user = {
        uid: row.uid,
        uname: row.uname,
        uaddress: row.uaddress,
        upn: row.upn,
      };
      // console.log(this.user)
      this.editDialogVisible = true;
    },
    showAddDialog() {
      this.user = {};
      this.dialogVisible = true;
    },
    handleSizeChange(newSize) {
      // alert('newSize'+newSize)
      this.tableData.total = newSize;
      this.tableData.pageNum = 1;
      this.getListUsers();
    },
    handleCurrentChange(current) {
      // alert(current)
      this.tableData.pageNum = current;
      this.getListUsers();
    },
    getListUsers() {
      // alert("hi")
      const that = this;
      this.$axios
        .post("/user/getUserList", this.tableData)
        .then(function (response) {
          console.log(response.data);
          that.userList = response.data.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    //查找用户
    getUsers() {
          // alert("hi")
          console.log(this.tableData);
          const that = this;
          this.userList=[];
      this.$axios
        .post("/user/getUser", this.tableData)
        .then(function (response) {
          console.log(response.data);
          that.userList = response.data.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
          this.user = {};
        })
        .catch((_) => {});
    },
    submitUserInfo() {
      const that = this;
      this.$axios
        .post("/user/saveUser", this.user)
        .then(function (response) {
          that.dialogVisible = false;
          that.getListUsers();
          that.$message.success("添加成功");
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    async delUser(id) {
      // const that = this
      console.log(id);
      const box = await this.$confirm(
        "此操作将永久删除该用户, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).catch((error) => error);
      if (box == "confirm") {
        const result = await this.$axios.post("/user/delUser?uid=" + id);
        console.log(result);
        this.getListUsers();
        this.$message.success("删除成功");
        return;
      } else {
        this.$message.success("取消成功");
      }
      //
    },
    async updateUserInfo() {
      const that = this;
      this.$axios.post("/user/saveUser", this.user).then(function (response) {
        console.log("hi")
        that.editDialogVisible = false;
        that.getListUsers();
        that.$message.success("添加成功");
      });
    },
  },
};
</script>
<style lang="less" scoped>
</style>
Footer
© 2022 GitHub, Inc.
Footer navigation
Terms
Privacy
Security
Status
Docs
Contact GitHub
Pricing
API
Training
Blog
About
