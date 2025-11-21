package com.example.mvvm_first.view.uicontroller


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm_first.model.DataJK.jenisK
import com.example.mvvm_first.viewmodel.SiswaViewModel


enum class Navigasi {
    FormulirAing,
    Detail
}
@Composable
fun DataApp(
    modifier: Modifier,
    viewModel: SiswaViewModel= viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold { isiRuang->
        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.FormulirAing.name,

            modifier = Modifier.padding(isiRuang)){
            composable(route = Navigasi.FormulirAing.name){
                val konteks = LocalContext.current
                _root_ide_package_.com.example.mvvm_first.view.FormIsian(
                    jenisK = jenisK.map{id -> konteks.resources.getString(id)},
                    OnSubmitBtnClick = {
                        viewModel.setSiswa(it)
                        navController.navigate(Navigasi.Detail.name)
                    }
                )
            }
            composable(route = Navigasi.Detail.name) {

                _root_ide_package_.com.example.mvvm_first.view.TampilData(
                    onBackBtnClick = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }

        }
    }
}

private fun cancelAndBackToFormulir(
    navController: NavHostController
) {
    navController.popBackStack(Navigasi.FormulirAing.name, inclusive = false)
}