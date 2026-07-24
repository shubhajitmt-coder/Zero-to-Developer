package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RoadmapRepository
    val taskProgressState: StateFlow<List<TaskProgress>>
    val dsaProgressState: StateFlow<List<DsaProgress>>

    // Navigation and Filtering state
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    private val _selectedYear = MutableStateFlow(1) // Year 1 or 2
    val selectedYear: StateFlow<Int> = _selectedYear.asStateFlow()

    private val _selectedMonth = MutableStateFlow(1) // Month 1..24
    val selectedMonth: StateFlow<Int> = _selectedMonth.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedDsaCategory = MutableStateFlow("All")
    val selectedDsaCategory: StateFlow<String> = _selectedDsaCategory.asStateFlow()

    init {
        val database = AppDatabase.getDatabase(application)
        repository = RoadmapRepository(database)

        taskProgressState = repository.taskProgressFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        dsaProgressState = repository.dsaProgressFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun selectTab(index: Int) {
        _selectedTab.value = index
    }

    fun selectYear(year: Int) {
        _selectedYear.value = year
        if (year == 1 && _selectedMonth.value > 12) {
            _selectedMonth.value = 1
        } else if (year == 2 && _selectedMonth.value <= 12) {
            _selectedMonth.value = 13
        }
    }

    fun selectMonth(month: Int) {
        _selectedMonth.value = month
        _selectedYear.value = if (month <= 12) 1 else 2
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectDsaCategory(category: String) {
        _selectedDsaCategory.value = category
    }

    fun toggleTaskCompletion(taskId: String, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleTaskCompletion(taskId, currentStatus)
        }
    }

    fun updateDsaSolvedCount(patternId: String, newCount: Int, targetCount: Int) {
        viewModelScope.launch {
            repository.updateDsaSolved(patternId, newCount, targetCount)
        }
    }

    // Repository getters for static data
    fun getCareerPaths() = repository.getCareerPaths()
    fun get24MonthRoadmap() = repository.get24MonthRoadmap()
    fun getLanguageStrategy() = repository.getLanguageStrategy()
    fun getDsaPatterns() = repository.getDsaPatterns()
    fun getCoreSubjects() = repository.getCoreSubjects()
    fun getPrimaryBackendStackInfo() = repository.getPrimaryBackendStackInfo()
    fun getPortfolioProjects() = repository.getPortfolioProjects()
    fun getSchedules() = repository.getSchedules()
    fun getInternshipReadyChecklist() = repository.getInternshipReadyChecklist()
    fun getPlacementStrategyInfo() = repository.getPlacementStrategyInfo()
    fun getSalaryTiers() = repository.getSalaryTiers()
    fun getSalaryBreakdownExplanation() = repository.getSalaryBreakdownExplanation()
    fun getMilestones() = repository.getMilestones()
    fun getResources() = repository.getResources()
    fun getThingsNotToLearnYet() = repository.getThingsNotToLearnYet()

    // Calculated Progress Metrics
    fun getOverallProgressPercentage(tasks: List<TaskProgress>): Float {
        val allRoadmapMonths = repository.get24MonthRoadmap()
        val totalTasksCount = allRoadmapMonths.sumOf { it.tasks.size }
        if (totalTasksCount == 0) return 0f
        val completedCount = tasks.count { it.isCompleted }
        return (completedCount.toFloat() / totalTasksCount.toFloat()) * 100f
    }

    fun calculateScorecardScore(tasks: List<TaskProgress>, dsaList: List<DsaProgress>): Int {
        val taskProgressPct = getOverallProgressPercentage(tasks)
        val dsaSolvedTotal = dsaList.sumOf { it.solvedCount }
        val dsaPct = (dsaSolvedTotal.toFloat() / 250f).coerceAtMost(1f) * 100f

        val score = (taskProgressPct * 0.60f) + (dsaPct * 0.40f)
        return score.toInt().coerceIn(0, 100)
    }
}
