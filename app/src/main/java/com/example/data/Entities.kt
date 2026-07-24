package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_progress")
data class TaskProgress(
    @PrimaryKey val taskId: String,
    val isCompleted: Boolean,
    val note: String = "",
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "dsa_progress")
data class DsaProgress(
    @PrimaryKey val patternId: String,
    val solvedCount: Int = 0,
    val targetCount: Int = 15,
    val isMastered: Boolean = false,
    val lastPracticedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_settings")
data class UserSetting(
    @PrimaryKey val key: String,
    val value: String
)

// UI Data Models for Content Presentation
data class CareerPathModel(
    val id: String,
    val title: String,
    val description: String,
    val fresherOpps: String, // e.g. "High", "Moderate", "Niche"
    val avgSalaryFresh: String, // e.g. "₹5 - 12 LPA"
    val longTermSalary: String, // e.g. "₹25 - 60+ LPA"
    val difficulty: String, // 1-5 scale
    val competition: String, // "Very High", "High", "Moderate"
    val mathRequired: String, // "Basic", "Moderate", "Advanced"
    val dsaImportance: String, // "Critical", "High", "Moderate"
    val bscSuitability: String, // "High (Equal footing)", "Moderate (B.Tech preferred)", etc.
    val aiAutomationRisk: String, // "Low", "Medium", "High"
    val isTop3: Boolean = false,
    val isRecommendedPrimary: Boolean = false,
    val ranking: Int = 0,
    val summaryWhy: String
)

data class MonthlyStageModel(
    val month: Int,
    val quarter: String, // Q1, Q2...
    val year: Int, // Year 1 or 2
    val title: String,
    val focusArea: String,
    val whatToLearn: List<String>,
    val whyNeeded: String,
    val depthLevel: String, // e.g., "Deep Conceptual + Hands-on Implementation"
    val prerequisites: String,
    val recommendedOrder: List<String>,
    val whatNotToLearnYet: List<String>,
    val practiceRequirements: String,
    val projectRequirements: String,
    val readinessCheck: List<String>,
    val tasks: List<RoadmapTaskModel>
)

data class RoadmapTaskModel(
    val id: String,
    val title: String,
    val subtext: String = ""
)

data class DsaPatternModel(
    val id: String,
    val category: String, // e.g., "Arrays & Two Pointers", "Sliding Window", "Trees", "DP"
    val title: String,
    val coreConcept: String,
    val keyProblemsCount: Int,
    val cppTemplate: String,
    val javaTemplate: String,
    val commonMistakes: List<String>,
    val interviewWeight: String // "Critical (Asked in 90% OAs)", "High", "Medium"
)

data class ProjectBlueprintModel(
    val id: String,
    val title: String,
    val tagline: String,
    val problemSolved: String,
    val techStack: List<String>,
    val dbSchemaDesign: String,
    val mainFeatures: List<String>,
    val authAndSecurity: String,
    val apiEndpoints: List<String>,
    val testingAndDeployment: String,
    val advancedFeatures: List<String>,
    val sampleInterviewQuestions: List<String>,
    val howToMakeItOriginal: String
)

data class CoreSubjectModel(
    val subject: String,
    val examFocus: String,
    val interviewFocus: String,
    val realDevUsage: String,
    val bscOverlapPercentage: String,
    val keyTopics: List<String>
)

data class ScheduleModel(
    val dayType: String, // "Tuition Day (Tue/Wed)", "Normal Day", "Weekend"
    val totalHours: String,
    val timeBreakdown: List<String>,
    val trainCommuteTip: String
)

data class ResourceModel(
    val topic: String,
    val primaryFree: String,
    val backupFree: String,
    val practicePlatform: String,
    val officialDocUrl: String
)

data class SalaryTierModel(
    val role: String,
    val ctcRange: String,
    val baseRange: String,
    val requirementsToAchieve: List<String>
)

data class MilestoneModel(
    val monthLabel: String, // "Month 3", "Month 6", etc.
    val know: List<String>,
    val build: List<String>,
    val solve: List<String>,
    val explain: List<String>,
    val resumePoints: List<String>
)
